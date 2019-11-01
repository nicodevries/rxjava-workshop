package nl.ing.mortgages.rxjava.excercise;

import io.reactivex.rxjava3.core.Observable;

public class ExcerciseWithIntegers {

    public long countNumberOfEvents(Observable<Integer> source) {
        return source.count().blockingGet();
    }

    public Observable<Integer> getEvenNumbers(Observable<Integer> integers) {
        return integers.filter(x -> x % 2 == 0);
    }
}
