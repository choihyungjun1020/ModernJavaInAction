package modernjavainaction.chap05;

import modernjavainaction.chap04.Dish;

import java.util.Optional;

import static modernjavainaction.chap04.Dish.menu;

/**
 * 특정 속성이 데이터 집합에 있는지 여부를 검색하는 데이터 처리
 * allMatch, anyMatch, noneMatch, findFirst, findAny, ...
 * anyMatch, allMatch, noneMatch
 * -> 위 세 메소드는 쇼트서킷 기법, 자바의 &&, ||와 같은 연산을 활용
 */
public class Finding {
    public static void main(String[] args) {
        if (isVegetarianFriendlyMenu()) {
            System.out.println("The menu is vegetarian friendly!!");
        }

        // stream 의 모든 요소는 1000kcal 보다 작은가? -> allMatch
        System.out.println(isHealthyMenu());
        // stream 의 모든 요소는 1000kcal 보다 크지 않은가? -> noneMatch
        System.out.println(isHealthyMenu2());

        Optional<Dish> dish = findVegetarianDish();
        dish.ifPresent(d -> System.out.println(d.getName()));
    }

    private static boolean isVegetarianFriendlyMenu() {
        return menu.stream().anyMatch(Dish::isVegetarian);
    }

    private static boolean isHealthyMenu() {
        return menu.stream().allMatch(dish -> dish.getCalories() < 1000);
    }

    /**
     * noneMatch 는 allMatch 와 반대 연산을 수행한다.
     * noneMatch 는 주어진 Predicate 와 일치하는 요소가 없는지 확인한다.
     */
    private static boolean isHealthyMenu2() {
        return menu.stream().noneMatch(dish -> dish.getCalories() >= 1000);
    }

    private static Optional<Dish> findVegetarianDish() {
        return menu.stream()
                .filter(Dish::isVegetarian)
                .findAny();
    }
}
