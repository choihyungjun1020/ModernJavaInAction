package modernjavainaction.chap04;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static modernjavainaction.chap04.Dish.menu;

public class StreamBasic {
    public static void main(String[] args) {
        List<String> threeHighCaloricDishNames = menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .collect(toList());

        System.out.println(threeHighCaloricDishNames);

        /**
         * 퀴즈 4-1
         * 아래 코드를 Stream API 로 리팩토링
         */
        List<String> highCaloricDishesIter = new ArrayList<>();
        Iterator<Dish> iterator = menu.iterator();
        while (iterator.hasNext()) {
            Dish dish = iterator.next();
            if (dish.getCalories() > 300) {
                highCaloricDishesIter.add(dish.getName());
            }
        }

        List<String> highCaloricDishesStream = menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .map(Dish::getName)
                .collect(toList());

        System.out.println("highCaloricDishesIter = " + highCaloricDishesIter);
        System.out.println("highCaloricDishesStream = " + highCaloricDishesStream);

        /**
         * 실습용 코드
         * 도서 내에서는 학습용으로 매우 좋은 기법이라고 서술.
         * 하지만 실무에서는... 절대 하지 말 것
         *
         * Stream 의 Lazy 특성
         * 1. limit 연산과 쇼트서킷에 의한 기법에 의해 오직 처음 3개만 선택 되었다. (5장)
         * 2. filter 와 map 은 서로 다른 연산이지만 한 과정으로 병합 되었다. (루프 퓨전)
         */
        List<String> names = menu.stream()
                .filter(dish -> {
                    System.out.println("filtering : " + dish.getName());
                    return dish.getCalories() > 300;
                })
                .map(dish -> {
                    System.out.println("mapping : " + dish.getName());
                    return dish.getName();
                })
                .limit(3)
                .collect(toList());

        System.out.println(names);

        names.stream().forEach(System.out::println);

        /**
         * 퀴즈 4-2
         * 스트림 파이프라인에서 중간 연산과 최종 연산을 구별
         *
         * 정답 : count() -> 최종연산, 나머지는 중간연산
         * Stream 을 return 하는 메소드는 중간연산이다.
         */
        long count = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .distinct()
                .limit(3)
                .count();
    }
}
