package modernjavainaction.chap03;

/**
 * 다음중 함수형 인터페이스는?
 */
public class FunctionalInterfaceQuiz {
    /**
     * 1. 람다를 사용할 수 있는 곳은 "함수형 인터페이스" 이다.
     * Comparator, Runnable, ActionListener, Callable, PrivilegedAction, ...
     * 많은 디폴트 메소드가 있더라도, 추상 메소드가 오직 하나면 "함수형 인터페이스"
     */
    interface Explanation {
    }

    interface Adder {
        int add(int a, int b);
    }

    interface SmartAdder extends Adder {
        int add(double a, double b);
    }

    interface Nothing {
    }

    // 정답은 Adder, SmartAdder 는 2개의 추상 메소드를 포함하므로 함수형 인터페이스가 아니다.
    // Nothing 은 추상 메소드가 없으므로 함수형 인터페이스가 아니다.
}
