package modernjavainaction.chap05;

import modernjavainaction.chap04.Dish;

import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static modernjavainaction.chap04.Dish.menu;

/**
 * 숫자형 스트림
 */
public class NumericStreams {
    public static void main(String[] args) {
        int calories = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();

        // 객체 스트림으로 복원하기, boxed()
        Stream<Integer> boxed = menu.stream()
                .mapToInt(Dish::getCalories)
                .boxed();

        // OptionalInt, IntStream 에서 0이라는 기본 값 때문에 잘못된 결과가 도출 될 수 있다.
        // OptionalInt, Double, Long 은 이를 방지한다.
        OptionalInt maxCalories = menu.stream()
                .mapToInt(Dish::getCalories)
                .max();

        int max = maxCalories.orElse(1);
        System.out.println("max = " + max);

        // 숫자 범위
        IntStream evenNumbers = IntStream.rangeClosed(1, 100)
                .filter(n -> n % 2 == 0);
        System.out.println(evenNumbers.count());

        // 피타고라스 수
        Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0).boxed()
                        .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                );
        pythagoreanTriples.limit(5).
                forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));

    }
}
