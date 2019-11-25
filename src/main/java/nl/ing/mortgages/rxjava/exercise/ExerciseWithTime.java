package nl.ing.mortgages.rxjava.exercise;

import io.reactivex.rxjava3.core.Observable;

import java.util.Arrays;

public class ExerciseWithTime {
    /**
     * In order to be able to make more sense out of unit test failure messages I'll describe the behaviour of the two sources:
     * source1 will emit a value every second for 10 seconds: [0,1,2,3,4,5,6,7,8,9]
     * source2 will emit a value every 3 seconds for 9 seconds: [100,101,102]
     */

    /**
     * In the following exercise you need to get all the items from the first source to return an item. Given that source1
     * emits an item after 1 second and source2 emits an item after 3 seconds, it is obvious that source1 will be the first.
     * To prevent you from just returning source1, there will be an extra test with different values to test that
     * your solution works for every situation
     *
     */
    public Observable<Long> getAllItemsFromFirstSourceThatGivesAResponse(Observable<Long> source1, Observable<Long> source2) {
        return Observable.amb(Arrays.asList(source1, source2));
    }

    /**
     * In the following exercise you need to sum the first item from source1 with the first item from source2, the second
     * item from source1 with the second item from source2, and so on, until one of the sources stops emitting items
     */
    public Observable<Long> sumItemsWithTheSameIndex(Observable<Long> source1, Observable<Long> source2) {
        return Observable.zip(source1, source2, Long::sum);
    }

    /**
     * In the following exercise you need to sum any item from any source with the most recently emitted item from the other
     * source. With our example inputs, this means that the third, fourth and fifth items of source1 should be summed with
     * the first item from source2, because the source1 emits three items for every item emitted by source2
     */
    public Observable<Long> sumItemsFromOneSourceWithLastEmittedItemFromSecondSource(Observable<Long> source1, Observable<Long> source2) {
        return Observable.combineLatest(source1, source2, Long::sum);
    }
}
