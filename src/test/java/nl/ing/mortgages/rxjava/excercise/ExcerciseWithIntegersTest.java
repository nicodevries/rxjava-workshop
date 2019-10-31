package nl.ing.mortgages.rxjava.excercise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExcerciseWithIntegersTest {
    private ExcerciseWithIntegers excercise;

    @BeforeEach
    void setUp() {
        excercise = new ExcerciseWithIntegers();
    }

    @Test
    void shouldCountHundredEvents() {
        assertEquals(100, excercise.countNumberOfEvents());
    }
}