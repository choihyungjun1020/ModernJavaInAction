package modernjavainaction.chap05.practice;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
 * p177, 실전 연습 문제
 */
public class PracticeMain {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        // 질의 1: 2011년부터 발생한 모든 거래를 찾아 값으로 정렬(작은 값에서 큰 값).
        List<Transaction> practiceOne = transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(toList());
        System.out.println("== practiceOne == \n" + practiceOne);

        // 질의 2: 거래자가 근무하는 모든 고유 도시는?
        List<String> practiceTwo = transactions.stream()
                .map(Transaction::getTrader)
                .map(trader -> trader.getCity())
                .distinct()
                .collect(toList());
        System.out.println("== practiceTwo == \n" + practiceTwo);

        // 질의 3: Cambridge 의 모든 거래자를 찾아 이름으로 정렬.
        List<Trader> practiceThree = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(toList());
        System.out.println("== practiceThree == \n" + practiceThree);

        // 질의 4: 알파벳 순으로 정렬된 모든 거래자의 이름 문자열을 반환
//        List<String> practiceFour = transactions.stream()
//                .map(Transaction::getTrader)
//                .map(Trader::getName)
//                .distinct()
//                .sorted()
//                .collect(toList());
//        System.out.println("== practiceFour == \n" + practiceFour);

        String practiceFour = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (n1, n2) -> n1 + n2);
//                .collect(joining());
        System.out.println("== practiceFour == \n" + practiceFour);

        // 질의 5: Milan 에 거주하는 거래자가 있는가?
        boolean practiceFive = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        System.out.println("== practiceFive == \n" + practiceFive);

        // 질의 6: Cambridge 에 사는 거래자의 모든 거래내역 출력.
        List<Integer> practiceSix = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .collect(toList());
        System.out.println("== practiceSix == \n" + practiceSix);

        // 질의 7: 모든 거래에서 최고값은 얼마인가?
        Optional<Integer> practiceSeven = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
//                .max(comparing(Transaction::getValue));
        System.out.println("== practiceSeven ==");
        practiceSeven.ifPresent(System.out::println);

        // 질의 8: 가장 작은 값을 가진 거래 탐색
        Optional<Transaction> practiceEight = transactions.stream()
                .min(comparing(Transaction::getValue));

        Optional<Transaction> practiceEightAnswer = transactions.stream()
                .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);

        System.out.println("== practiceEight ==");
        practiceEight.ifPresent(System.out::println);
    }
}
