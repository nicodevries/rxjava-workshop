package nl.ing.mortgages.rxjava.excercise;

import io.reactivex.rxjava3.core.Observable;
import nl.ing.mortgages.rxjava.Observables;

public class ExcerciseWithIntegers {
    private Observable<Integer> integers = Observables.integers();

    public long countNumberOfEvents() {
        return integers.count().blockingGet();
    }
}
