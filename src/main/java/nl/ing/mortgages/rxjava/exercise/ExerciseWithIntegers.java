package nl.ing.mortgages.rxjava.exercise;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class ExerciseWithIntegers {

    public long countNumberOfEvents(Observable<Integer> source) {
        return source.count().blockingGet();
    }

    public Observable<Integer> getEvenNumbers(Observable<Integer> integers) {
        return integers.filter(x -> x % 2 == 0);
    }

    public Single<Integer> getSumOfEvenNumbers(Observable<Integer> integers) {
        return getEvenNumbers(integers).reduce(0, Integer::sum);
    }


    /** The next exercise is a little bit more intricate. For each integer n in the input, create
     * a sequence of 0..n and output all those sequences as one long sequence.
     *
     * For instance: if the input is [0, 1, 2, 3], the output should be [0, 0,1, 0,1,2, 0,1,2,3]
     */
    public Observable<Integer> getSequenceOfCountingUpToEachInteger(Observable<Integer> integers) {
       return integers.flatMap(x -> Observable.range(0, x + 1));
    }
}
