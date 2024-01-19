package modernjavainaction.chap01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilteringApples {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(30, "green"),
                new Apple(155, "green"),
                new Apple(120, "red")
        );

        List<Apple> greenApples = filterApples(inventory, FilteringApples::isGreenApple);
        for (Apple greenApple : greenApples) {
            System.out.println("greenApple = " + greenApple.getColor());
        }

        List<Apple> heavyApples = filterApples(inventory, FilteringApples::isHeavyApple);
        for (Apple heavyApple : heavyApples) {
            System.out.println("heavyApple = " + heavyApple.getWeight());
        }

        // Use Lambda(Anonymous Function)
        List<Apple> greenApples2 = filterApples(inventory, (Apple a) -> "green".equals(a.getColor()));
        for (Apple apple : greenApples2) {
            System.out.println("green apple Use Lambda = " + apple.getColor());
        }

        List<Apple> heavyApples2 = filterApples(inventory, (Apple a) -> a.getWeight() > 150);
        for (Apple apple : heavyApples2) {
            System.out.println("heavy apple Use Lambda = " + apple.getWeight());
        }

        List<Apple> weirdApples = filterApples(inventory, (Apple a) -> a.getWeight() < 80 || "red".equals(a.getColor()));
        for (Apple weirdApple : weirdApples) {
            System.out.println("weird apple \nweight = " + weirdApple.getWeight() + "\ncolor = " + weirdApple.getColor());
        }
    }

    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterHeavyApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > 150) {
                result.add(apple);
            }
        }
        return result;
    }

    // 코드의 중복, foreach 내의 조건을 제외하곤 모두 같다.
    // Java 8 에서는 코드를 인수로 넘겨줄 수 있으므로, filter 를 구현하지 않아도 된다.
    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }

    // 함수형 인터페이스,
    public interface Predicate<T> {
        boolean test(T t);
    }

    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }
}
