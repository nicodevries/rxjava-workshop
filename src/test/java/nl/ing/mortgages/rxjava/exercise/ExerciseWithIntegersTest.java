package nl.ing.mortgages.rxjava.exercise;

import io.reactivex.rxjava3.core.Observable;
import nl.ing.mortgages.rxjava.Observables;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExerciseWithIntegersTest {
    private ExerciseWithIntegers exercise;
    private Observable<Integer> integers;

    @BeforeEach
    void setUp() {
        exercise = new ExerciseWithIntegers();
    }

    @ParameterizedTest
    @ValueSource(ints = { 10, 20, 50, 100 })
    void shouldCountHundredEvents(int expectedCount) {
        integers = Observables.integers(expectedCount);
        assertEquals(expectedCount, exercise.countNumberOfEvents(integers));
    }

    @ParameterizedTest
    @ValueSource(ints = { 10, 20, 50, 100 })
    void shouldContainOnlyEvenNumbers(int numberOfInputs) {
        integers = Observables.integers(numberOfInputs);
        exercise.getEvenNumbers(integers).test().assertValueCount(numberOfInputs / 2);
        exercise.getEvenNumbers(integers).all(x -> x % 2 == 0).test().assertValue(true);
    }

    @ParameterizedTest
    @ValueSource(ints = { 10, 20, 50, 100 })
    void shouldCalculateTheSumOfAllEvenNumbers(int numberOfInputs) {
        integers = Observables.integers(numberOfInputs);
        Integer expectedResult = (numberOfInputs/2) * ((numberOfInputs/2) - 1);
        exercise.getSumOfEvenNumbers(integers).test().assertValue(expectedResult);
    }

    @Test
    void shouldHaveASequenceOfCountsUpToInput() {
        integers = Observables.integers(4);
        exercise.getSequenceOfCountingUpToEachInteger(integers).test().assertResult(0,0,1,0,1,2,0,1,2,3);
    }

    @Test
    void shouldHaveASequenceOfCountsUpToInput_NotStartingAtZero() {
        integers = Observable.range(3, 3);
        exercise.getSequenceOfCountingUpToEachInteger(integers).test().assertResult(0,1,2,3,0,1,2,3,4,0,1,2,3,4,5);
    }
}