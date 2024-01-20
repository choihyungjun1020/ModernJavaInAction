package modernjavainaction.chap03;

import java.util.concurrent.Callable;

/**
 * 다음중 람다 표현식을 올바로 사용한 코드는?
 */
public class LambdaCaseQuiz {

    public static void main(String[] args) {
        // 1
        execute(() -> {
        });

        // 3
        // Predicate<Apple> p = (Apple a) -> a.getWeight();
    }

    public static void execute(Runnable r) {
        r.run();
    }

    // 2
    public static Callable<String> fetch() {
        return () -> "Tricky example";
    }
}

/**
 * 정답은 1번과 2번
 * <p>
 * 1번의 시그니처는 () -> void 이며, Runnable 의 추상 메소드 시그니처와 일치하므로 유효한 람다 표현식
 * <p>
 * 2번의 fetch 메서드의 반환 형식은 Callable<String>
 * T 를 String 으로 대치했을 때 Callable<String> 으로 대치했을 때 메서드 시그니처는 () -> String 이 된다.
 * () -> String == () -> "Tricky example" 이므로 올바른 형식
 * <p>
 * 3번의 예제는 Predicate 의 test 메소드 시그니처는 (T t) -> boolean
 * 하지만 3번의 람다 표현식은 (T t) -> Integer 이므로 메소드 시그니처가 일치하지 않음 == 유효하지 않다.
 */