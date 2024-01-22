package modernjavainaction.chap05;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static modernjavainaction.chap04.Dish.menu;

/**
 * reduce()
 * 리듀싱 연산, 모든 스트림 요소를 처리해서 값으로 도출한다.
 */
public class Reducing {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3, 4, 5, 1, 2);
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println("sum = " + sum);

        int sumMethod = numbers.stream().reduce(0, Integer::sum);
        System.out.println("sumMethod = " + sumMethod);

        // 초기값이 없는 경우
        Optional<Integer> sumOptional = numbers.stream().reduce(Integer::sum);
        System.out.println("sumOptional = " + sumOptional.get());

        int max = numbers.stream().reduce(0, (a, b) -> Integer.max(a, b));
        System.out.println("max = " + max);

        Optional<Integer> min = numbers.stream().reduce(Integer::min);
        System.out.print("min = ");
        min.ifPresent(System.out::println);

        Optional<Integer> count = menu.stream()
                .map(dish -> 1)
                .reduce(Integer::sum);
        count.ifPresent(System.out::println);
    }
}
