package modernjavainaction.chap03;

import modernjavainaction.chap02.Apple;
import modernjavainaction.chap02.Color;
import modernjavainaction.chap02.predicate.ApplePredicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Lambda {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(80, Color.GREEN),
                new Apple(155, Color.GREEN),
                new Apple(120, Color.RED)
        );

        Comparator<Apple> byWeightAnonClass = new Comparator<>() {
            public int compare(Apple a1, Apple a2) {
                return a1.getWeight().compareTo(a2.getWeight());
            }
        };

        /**
         * 2. 람다 표현식으로 "함수형 인터페이스의 추상 메소드 구현"을 직접 전달할 수 있으므로
         * 전체 표현식을 함수형 인터페이스의 인스턴스로 취급
         * -> 기술적으로 따지면 함수형 인터페이스를 구현한 클래스의 인스턴스
         */
        Comparator<Apple> byWeightLambda = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());

        inventory.sort(byWeightLambda);
        System.out.println(inventory);

        List<Apple> greenApples = filter(inventory, (Apple a) -> Color.GREEN.equals(a.getColor()));
        System.out.println(greenApples);

        /**
         * 3. 덜 깔끔하지만 익명 내부 클래스로도 같은 기능을 구현 가능
         */
        // Lambda
        Runnable r1 = () -> System.out.println("Hello World 1");
        // Anonymous Class
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World 2");
            }
        };

        process(r1);
        process(r2);
        process(() -> System.out.println("Hello World 3")); // 직접 전달된 람다 표현식으로 출력, 해당 클래스의 인스턴스를 직접 전달 한 것이다.
    }

    public static List<Apple> filter(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static void process(Runnable r) {
        r.run();
    }
}
