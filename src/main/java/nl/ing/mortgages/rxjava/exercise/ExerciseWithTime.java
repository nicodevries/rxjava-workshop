package nl.ing.mortgages.rxjava.exercise;

import io.reactivex.rxjava3.core.Observable;

import java.util.Arrays;

public class ExerciseWithTime {
    /**
     * In order to be able to make more sense out of unit test failure messages I'll describe the behaviour of the two sources:
     * source1 will emit a value every second for 10 seconds: [0,1,2,3,4,5,6,7,8,9]
     * source2 will emit a value every 3 seconds for 9 seconds: [100,101,102]
     */


    public Observable<Long> getAllItemsFromFirstSourceThatGivesAResponse(Observable<Long> source1, Observable<Long> source2) {
        return Observable.amb(Arrays.asList(source1, source2));
    }

    public Observable<Long> sumItemsWithTheSameIndex(Observable<Long> source1, Observable<Long> source2) {
        return Observable.zip(source1, source2, Long::sum);
    }

    public Observable<Long> sumItemsFromOneSourceWithLastEmittedItemFromSecondSource(Observable<Long> source1, Observable<Long> source2) {
        return Observable.combineLatest(source1, source2, Long::sum);
    }
}
