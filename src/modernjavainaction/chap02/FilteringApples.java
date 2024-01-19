package modernjavainaction.chap02;

import modernjavainaction.chap02.predicate.AppleGreenColorPredicate;
import modernjavainaction.chap02.predicate.ApplePredicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FilteringApples {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(80, Color.GREEN),
                new Apple(155, Color.GREEN),
                new Apple(120, Color.RED));

        // [Apple{color=GREEN, weight=80}, Apple{color=GREEN, weight=155}]
        List<Apple> greenApples = filterApplesByColor(inventory, Color.GREEN);
        System.out.println("greenApples = " + greenApples);

        // [Apple{color=RED, weight=120}]
        List<Apple> redApples = filterApplesByColor(inventory, Color.RED);
        System.out.println("redApples = " + redApples);

        // [Apple{color=GREEN, weight=80}, Apple{color=GREEN, weight=155}]
        List<Apple> greenApples2 = filterApples(inventory, new AppleGreenColorPredicate());
        System.out.println("greenApples2 = " + greenApples2);

        /**
         * 다섯 번째 시도, 익명 클래스 사용
         */
        List<Apple> redApples2 = filterApples(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return Color.RED == apple.getColor();
            }
        });
        System.out.println("redApples2 = " + redApples2);

        /**
         * 여섯 번째 시도, 람다 표현식 사용
         */
        List<Apple> redApples3 = filterApples(inventory, (Apple apple) -> Color.RED == apple.getColor());
        System.out.println("redApples3 = " + redApples3);

        /**
         * 실전 예제 1, Comparator 로 정렬하기
         */
        System.out.println(inventory);

        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple a1, Apple a2) {
                return a1.getWeight().compareTo(a2.getWeight());
            }
        });
        // Lambda
        inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));

        System.out.println(inventory);

        /**
         * 실전 예제 2, Runnerble 로 코드 블록 실행하기
         */
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread1 run");
            }
        });
        t1.start();

        Thread t2 = new Thread(() -> System.out.println("thread2 run"));
        t2.start();
    }

    /**
     * 첫 번째 시도, 녹색 사과를 필터링.
     */
    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getColor() == Color.GREEN) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 두 번째 시도, 붉은 사과도 필터링, Color 를 파라미터화
     */
    public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getColor() == color) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 세 번째 시도, 무게로도 필터링, 추가 메소드를 사용
     * 하지만 이 코드는 filterApplesByColor 와 대부분 중복
     * DRY(Don't Repeat Yourself) 원칙을 위배함
     */
    public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > weight) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 네 번째 시도, 동적 파라미터화
     * 동적 파라미터화 : 필터링의 기준을 전달할 수 있다
     */
    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }
}
