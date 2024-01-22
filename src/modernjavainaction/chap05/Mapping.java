package modernjavainaction.chap05;

import modernjavainaction.chap04.Dish;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static modernjavainaction.chap04.Dish.menu;

/**
 * map(), parameter 에 제공된 함수는 각 요소에 적용된다.
 * 함수를 적용한 결과가 새로운 요소로 매핑된다.
 * '기존의 값을 고친다.' 라는 개념보다는 '새로운 버전을 만든다.' 라는 개념에 가까움.
 */
public class Mapping {
    public static void main(String[] args) {
        // map
        List<String> dishNames = menu.stream()
                .map(Dish::getName)
                .collect(toList());
        System.out.println(dishNames);

        // map
        List<String> words = Arrays.asList("Modern", "Java", "In", "Action");
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(toList());
        System.out.println(wordLengths);

        // map
        List<Integer> dishNameLengths = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(toList());
        System.out.println(dishNameLengths);

        // flat map, List<String[]> Human Error🤔
        List<String> newWords = Arrays.asList("Hello", "World");
        List<String[]> newSplitWords = newWords.stream()
                .map(word -> word.split(""))
                .collect(toList());
        System.out.println(newSplitWords);

        // Use flatMap()
        List<String> uniqueCharacters = newWords.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());
        System.out.println(uniqueCharacters);

        /**
         * Quiz 5-2
         * 1. 숫자 리스트가 주어졌을 때 각 숫자의 제곱근으로 이루어진 리스트를 반환.
         * [1, 2, 3, 4, 5] -> [1, 4, 9, 16, 25]
         */
        List<Integer> quizList = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> squares = quizList.stream()
                .map(x -> x * x)
                .collect(toList());
        System.out.println(squares);

        /**
         * 2. 두 개의 숫자 리스트가 있을 때 모든 숫자 쌍의 리스트를 반환
         * [1, 2, 3], [3, 4] -> [(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]
         */
        List<Integer> quizList1 = Arrays.asList(1, 2, 3);
        List<Integer> quizList2 = Arrays.asList(3, 4);
        List<int[]> pairs = quizList1.stream()
                .flatMap(i -> quizList2.stream()
                        .map(j -> new int[]{i, j})
                )
                .collect(toList());
        pairs.forEach(pair -> System.out.printf("(%d, %d)", pair[0], pair[1]));

        System.out.println();

        /**
         * 3. 이전 예제에서 합이 3으로 나누어 떨어지는 쌍만 반환하려면?
         * [1, 2, 3], [3, 4] -> [(2, 4), (3, 3)]
         */
        List<int[]> pairs2 = quizList1.stream()
                .flatMap(i -> quizList2.stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[]{i, j})
                )
                .collect(toList());
        pairs2.forEach(pair -> System.out.printf("(%d, %d)", pair[0], pair[1]));
    }
}
