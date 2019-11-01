package nl.ing.mortgages.rxjava.excercise;

import io.reactivex.rxjava3.core.Observable;
import nl.ing.mortgages.rxjava.Observables;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExcerciseWithIntegersTest {
    private ExcerciseWithIntegers excercise;
    private Observable<Integer> integers;

    @BeforeEach
    void setUp() {
        excercise = new ExcerciseWithIntegers();
    }

    @ParameterizedTest
    @ValueSource(ints = { 10, 20, 50, 100 })
    void shouldCountHundredEvents(int expectedCount) {
        integers = Observables.integers(expectedCount);
        assertEquals(expectedCount, excercise.countNumberOfEvents(integers));
    }

    @ParameterizedTest
    @ValueSource(ints = { 10, 20, 50, 100 })
    void shouldContainOnlyEvenNumbers(int numberOfInputs) {
        integers = Observables.integers(numberOfInputs);
        excercise.getEvenNumbers(integers).test().assertValueCount(numberOfInputs / 2);
        excercise.getEvenNumbers(integers).all(x -> x % 2 == 0).test().assertValue(true);
    }
}