package nl.ing.mortgages.rxjava.excercise;

import io.reactivex.rxjava3.core.Notification;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.observers.TestObserver;
import io.reactivex.rxjava3.schedulers.TestScheduler;
import nl.ing.mortgages.rxjava.Observables;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

class ExcerciseWithTimeTest {
    private ExcerciseWithTime excercise;
    private Observable<Long> fasterSource;
    private Observable<Long> slowerSource;
    private TestScheduler scheduler;

    @BeforeEach
    void setUp() {
        excercise = new ExcerciseWithTime();
        scheduler = new TestScheduler();
        int fastPeriod = 1;
        int slowPeriod = 3;
        fasterSource = Observables.interval(fastPeriod, scheduler).doOnEach(logForSource(1, fastPeriod)).take(10).doOnEach(System.out::println);
        slowerSource = Observables.interval(slowPeriod, scheduler).doOnEach(logForSource(2, slowPeriod)).map(x -> x + 100).take(3).doOnEach(System.out::println);
    }

    @Test
    void shouldEmitAllTheItemsOfTheSourceThatReturnsTheFirstResponse() {
        TestObserver<Long> observer = excercise.getAllItemsFromFirstSourceThatGivesAResponse(fasterSource, slowerSource).test();

        scheduler.advanceTimeTo(10, TimeUnit.SECONDS);

        observer.assertValueSequence(LongStream.range(0, 10).boxed().collect(Collectors.toList()));
    }

    @Test
    void shouldSumItemsWithTheSameIndex() {
        TestObserver<Long> observer = excercise.sumItemsWithTheSameIndex(fasterSource, slowerSource).test();

        scheduler.advanceTimeTo(10, TimeUnit.SECONDS);

        observer.assertResult(100L,102L,104L);
    }

    @Test
    void shouldSumLastItemFromOneSourceWithLastEmittedItemFromSecondSource() {
        TestObserver<Long> observer = excercise.sumItemsFromOneSourceWithLastEmittedItemFromSecondSource(fasterSource, slowerSource).test();

        scheduler.advanceTimeTo(10, TimeUnit.SECONDS);

        observer.assertResult(101L, 102L, 103L, 104L, 105L, 106L, 107L, 108L, 109L, 110L, 111L);
    }

    private Consumer<Notification<Long>> logForSource(int sourceNumber, int period) {
        return x -> System.out.println(
                String.format("Source %d - %d seconds : %d", sourceNumber, (x.getValue() + 1) * period, x.getValue())
        );
    }
}