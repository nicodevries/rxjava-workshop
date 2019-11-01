package nl.ing.mortgages.rxjava.excercise;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observers.TestObserver;
import io.reactivex.rxjava3.schedulers.TestScheduler;
import nl.ing.mortgages.rxjava.Observables;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.*;

class ExcerciseWithTimeTest {
    private ExcerciseWithTime excercise;
    private Observable<Long> fasterSource;
    private Observable<Long> slowerSource;
    private TestScheduler scheduler;

    @BeforeEach
    void setUp() {
        excercise = new ExcerciseWithTime();
        scheduler = new TestScheduler();
        fasterSource = Observables.interval(1, scheduler).take(10);
        slowerSource = Observables.interval(3, scheduler).map(x -> x + 100).take(3);
    }

    @Test
    void shouldEmitAllTheItemsOfTheSourceThatReturnsTheFirstResponse() {
        TestObserver<Long> observer = excercise.getAllItemsFromFirstSourceThatGivesAResponse(fasterSource, slowerSource).test();

        scheduler.advanceTimeTo(10, TimeUnit.SECONDS);

        observer.assertValueSequence(LongStream.range(0, 10).boxed().collect(Collectors.toList()));
    }
}