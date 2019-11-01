package nl.ing.mortgages.rxjava.excercise;

import io.reactivex.rxjava3.core.Observable;

import java.util.Arrays;

public class ExcerciseWithTime {
    public Observable<Long> getAllItemsFromFirstSourceThatGivesAResponse(Observable<Long> source1, Observable<Long> source2) {
        return Observable.amb(Arrays.asList(source1, source2));
    }

    public Observable<Long> sumItemsWithTheSameIndex(Observable<Long> source1, Observable<Long> source2) {
        return Observable.zip(source1, source2, Long::sum);
    }
}
