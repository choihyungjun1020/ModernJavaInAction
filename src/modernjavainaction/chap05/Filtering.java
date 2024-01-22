package modernjavainaction.chap05;

import modernjavainaction.chap04.Dish;
import modernjavainaction.chap04.Type;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static modernjavainaction.chap04.Dish.menu;

public class Filtering {
    public static void main(String[] args) {
        System.out.println("==Filtering with a predicate==");
        List<Dish> vegetarianMenu = menu.stream()
                .filter(Dish::isVegetarian)
                .collect(toList());
        vegetarianMenu.forEach(System.out::println);

        // 고유 요소로 거름, distinct()
        System.out.println("==Filtering unique elements==");
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);

        // 스트림 슬라이스
        // 칼로리 값을 기준으로 리스트를 오름차순 정렬!
        List<Dish> specialMenu = Arrays.asList(
                new Dish("season fruit", true, 120, Type.OTHER),
                new Dish("prawns", false, 300, Type.FISH),
                new Dish("rice", true, 350, Type.OTHER),
                new Dish("chicken", false, 400, Type.MEAT),
                new Dish("french fries", true, 530, Type.OTHER));
        System.out.println("==Filtered sorted menu==");
        List<Dish> filteredMenu = specialMenu.stream()
                .filter(Dish::isVegetarian)
                .collect(toList());
        System.out.println(filteredMenu);


        /**
         * Stream Slice, takeWhile()
         *  Predicate 의 결과가 true 인 요소에 대한 필터링.
         *  Predicate 가 처음으로 거짓이 되는 지점에 연산을 멈춘다.
         */
        System.out.println("==Sorted menu sliced with takeWhile()==");
        List<Dish> slicedMenu1 = specialMenu.stream()
                .takeWhile(dish -> dish.getCalories() < 320)
                .collect(toList());
        slicedMenu1.forEach(System.out::println);


        /**
         * Stream Slice, dropWhile()
         * dropWhile 은 takeWhile 과 정반대의 작업을 수행한다.
         * dropWhile 은 Predicate 가 처음 거짓이 되는 지점 전까지 발견된 요소를 버린다.
         */
        System.out.println("==Sorted menu sliced with dropWhile()==");
        List<Dish> slicedMenu2 = specialMenu.stream()
                .dropWhile(dish -> dish.isVegetarian())
                .collect(toList());
        slicedMenu2.forEach(System.out::println);

        // Stream 축소, limit()
        System.out.println("==Truncating a stream==");
        List<Dish> dishes = specialMenu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .limit(3)
                .collect(toList());
        dishes.forEach(System.out::println);

        // 요소 생략, skip()
        System.out.println("==Skipping elements==");
        List<Dish> skipDishes = specialMenu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .skip(2)
                .collect(toList());
        skipDishes.forEach(System.out::println);


        /**
         * Quiz 5-1
         * 스트림을 이용해서 처음 등장하는 두 고기 요리를 필터링
         */
        System.out.println("==Quiz 5-1==");
        List<Dish> twoMeats = menu.stream()
                .filter(dish -> dish.getType() == Type.MEAT)
                .limit(2)
                .collect(toList());
        twoMeats.forEach(System.out::println);
    }
}
