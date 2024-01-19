package modernjavainaction.chap02.predicate;

import modernjavainaction.chap02.Apple;

public class AppleWeightPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }
}
