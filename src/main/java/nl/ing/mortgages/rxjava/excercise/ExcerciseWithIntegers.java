package nl.ing.mortgages.rxjava.excercise;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class ExcerciseWithIntegers {

    public long countNumberOfEvents(Observable<Integer> source) {
        return source.count().blockingGet();
    }

    public Observable<Integer> getEvenNumbers(Observable<Integer> integers) {
        return integers.filter(x -> x % 2 == 0);
    }

    public Single<Integer> getSumOfEvenNumbers(Observable<Integer> integers) {
        return getEvenNumbers(integers).reduce(0, Integer::sum);
    }
}
