package nl.ing.mortgages.rxjava.excercise;

import io.reactivex.rxjava3.core.Observable;
import nl.ing.mortgages.rxjava.Observables;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExcerciseWithIntegersTest {
    private ExcerciseWithIntegers excercise;
    private Observable<Integer> integers;

    @BeforeEach
    void setUp() {
        integers = Observables.integers();
        excercise = new ExcerciseWithIntegers();
    }

    @Test
    void shouldCountHundredEvents() {
        assertEquals(100, excercise.countNumberOfEvents(integers));
    }
}