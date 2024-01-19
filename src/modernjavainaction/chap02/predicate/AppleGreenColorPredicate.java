package modernjavainaction.chap02.predicate;

import modernjavainaction.chap02.Apple;
import modernjavainaction.chap02.Color;

public class AppleGreenColorPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return apple.getColor() == Color.GREEN;
    }
}
