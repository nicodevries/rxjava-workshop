package nl.ing.mortgages.rxjava;

import io.reactivex.rxjava3.core.Observable;

public class Observables {
    public static Observable<Integer> integers() {
        return Observable.range(0, 100);
    }
}
