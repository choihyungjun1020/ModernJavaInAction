package modernjavainaction.chap09;

import modernjavainaction.chap02.Apple;
import modernjavainaction.chap02.Color;
import modernjavainaction.chap06.CaloricLevel;
import modernjavainaction.chap06.Dish;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static modernjavainaction.chap06.Dish.menu;

/**
 * 1. 익명 클래스를 람다 표현식으로 리팩터링 하기 -> 명시적 형변환도 사용할 수 있음.
 * 2. 람다 표현식을 메소드 참조로 리팩터링 하기 -> 가독성이 높아짐
 * 3. 명령형 데이터 처리를 스트림으로 리팩터링 하기 -> 데이터 처리 파이프 라인의 의도를 명확히 해준다.
 */
public class Refactoring {
    public static void main(String[] args) {
        // 1 Anonymous Class to Lambda
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello");
            }
        };
        r1.run();

        Runnable r2 = () -> System.out.println("Hello");
        r2.run();

        // 2 - 1 Lambda to Method Reference
        List<Apple> inventory = Arrays.asList(
                new Apple(80, Color.GREEN),
                new Apple(155, Color.GREEN),
                new Apple(120, Color.RED));

        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream()
                .collect(groupingBy(dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) {
                        return CaloricLevel.NORMAL;
                    } else {
                        return CaloricLevel.FAT;
                    }
                }));

        menu.stream().collect(groupingBy(Dish::getCaloricLevel)); // getCaloricLevel 구현 (메소드를 추가)

        // 2 - 2
        inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));
        inventory.sort(comparing(Apple::getWeight));

        // 3
        List<String> dishNames = new ArrayList<>();
        for (Dish dish : menu) {
            if (dish.getCalories() > 300) {
                dishNames.add(dish.getName());
            }
        }

        List<String> dishNamesStream = menu.parallelStream()
                .filter(d -> d.getCalories() > 300)
                .map(Dish::getName)
                .collect(toList());

    }
}
