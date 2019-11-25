package nl.ing.mortgages.rxjava.exercise;

import io.reactivex.rxjava3.core.Notification;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.observers.TestObserver;
import io.reactivex.rxjava3.schedulers.TestScheduler;
import nl.ing.mortgages.rxjava.Observables;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

class ExerciseWithTimeTest {
    private ExerciseWithTime exercise;
    private Observable<Long> fasterSource;
    private Observable<Long> slowerSource;
    private TestScheduler scheduler;

    @BeforeEach
    void setUp() {
        exercise = new ExerciseWithTime();
        scheduler = new TestScheduler();
        int fastPeriod = 1;
        int slowPeriod = 3;
        fasterSource = Observables.interval(fastPeriod, scheduler).doOnEach(logForSource(1, fastPeriod)).take(10).doOnEach(System.out::println);
        slowerSource = Observables.interval(slowPeriod, scheduler).doOnEach(logForSource(2, slowPeriod)).map(x -> x + 100).take(3).doOnEach(System.out::println);
    }

    @Nested
    @DisplayName("Get all the items of the first source to emit one item")
    class AllFromFirst {

        @Test
        @DisplayName("Test with the values as described in the exercise")
        void shouldEmitAllTheItemsOfTheSourceThatReturnsTheFirstResponse() {
            TestObserver<Long> observer = exercise.getAllItemsFromFirstSourceThatGivesAResponse(fasterSource, slowerSource).test();

            scheduler.advanceTimeTo(10, TimeUnit.SECONDS);

            observer.assertValueSequence(LongStream.range(0, 10).boxed().collect(Collectors.toList()));
        }

        @Test
        @DisplayName("Test with different values")
        void shouldEmitAllTheItemsOfTheSourceThatReturnsTheFirstResponse_DifferentValues() {
            TestObserver<Long> observer = exercise.getAllItemsFromFirstSourceThatGivesAResponse(slowerSource, fasterSource).test();

            scheduler.advanceTimeTo(10, TimeUnit.SECONDS);

            observer.assertValueSequence(LongStream.range(0, 10).boxed().collect(Collectors.toList()));
        }
    }

    @Test
    @DisplayName("Sum items with the same index")
    void shouldSumItemsWithTheSameIndex() {
        TestObserver<Long> observer = exercise.sumItemsWithTheSameIndex(fasterSource, slowerSource).test();

        scheduler.advanceTimeTo(10, TimeUnit.SECONDS);

        observer.assertResult(100L,102L,104L);
    }

    @Test
    @DisplayName("Sum last item from one source with last emitted item from second source")
    void shouldSumLastItemFromOneSourceWithLastEmittedItemFromSecondSource() {
        TestObserver<Long> observer = exercise.sumItemsFromOneSourceWithLastEmittedItemFromSecondSource(fasterSource, slowerSource).test();

        scheduler.advanceTimeTo(10, TimeUnit.SECONDS);

        observer.assertResult(101L, 102L, 103L, 104L, 105L, 106L, 107L, 108L, 109L, 110L, 111L);
    }

    private Consumer<Notification<Long>> logForSource(int sourceNumber, int period) {
        return x -> System.out.println(
                String.format("Source %d - %d seconds : %d", sourceNumber, (x.getValue() + 1) * period, x.getValue())
        );
    }
}