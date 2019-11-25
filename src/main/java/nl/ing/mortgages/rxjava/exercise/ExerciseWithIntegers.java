package nl.ing.mortgages.rxjava.exercise;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class ExerciseWithIntegers {

    public Observable<Integer> addFiveToEachNumber(Observable<Integer> integers) {
        return integers;
    }

    public Observable<Integer> getEvenNumbers(Observable<Integer> integers) {
        return integers;
    }

    public Single<Integer> getSumOfEvenNumbers(Observable<Integer> integers) {
        return Single.never();
    }


    /** The next exercise is a little bit more intricate. For each integer n in the input, create
     * a sequence of 0..n and output all those sequences as one long sequence.
     *
     * For instance: if the input is [0, 1, 2, 3], the output should be [0, 0,1, 0,1,2, 0,1,2,3]
     */
    public Observable<Integer> getSequenceOfCountingUpToEachInteger(Observable<Integer> integers) {
       return integers;
    }

    /** In the next exercise you need to do something that goes against the principles of Reactive Extensions:
     * Blocking. In order to count the number of events from a source, you first need to be sure that it has
     * completed. Usually you only want one of these 'terminal' operators. Don't break the chain!
     */
    public long countNumberOfEvents(Observable<Integer> source) {
        return 0;
    }
}
