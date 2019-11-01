package nl.ing.mortgages.rxjava;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;

import java.util.concurrent.TimeUnit;

import static java.util.Objects.isNull;

public class Observables {
    public static Observable<Integer> integers(int count) {
        return Observable.range(0, count);
    }
    public static Observable<Long> interval(int period, Scheduler scheduler) {
        if (isNull(scheduler)) {
            return Observable.interval(period, TimeUnit.SECONDS);
        } else {
            return Observable.interval(period, TimeUnit.SECONDS, scheduler);
        }
    }
}
