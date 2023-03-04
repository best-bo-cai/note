package com.liu.stream;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class Test {
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
//    (1) 找出2011年发生的所有交易，并按交易额排序（从低到高）。
        List<Transaction> collect = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        log.info(collect.toString());
//    (2) 交易员都在哪些不同的城市工作过？
        List<String> collect1 = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        log.info(collect1.toString());
//    (3) 查找所有来自于剑桥的交易员，并按姓名排序。
        List<Trader> cambridge = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        log.info(cambridge.toString());
//    (4) 返回所有交易员的姓名字符串，按字母顺序排序。
        String reduce = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted(String::compareTo)
                .collect(Collectors.joining());
//                .reduce(" ", (a, b) -> a + b);
        log.info(reduce);
//    (5) 有没有交易员是在米兰工作的？
        boolean milan = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        System.out.println(milan);
//    (6) 打印生活在剑桥的交易员的所有交易额。
        transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(transaction -> transaction.getValue())
                .forEach(System.out::println);
//    (7) 所有交易中，最高的交易额是多少？
        Integer reduce1 = transactions.stream()
                .map(transaction -> transaction.getValue())
                .reduce(0, (a, b) -> a > b ? a : b);
        System.out.println(reduce1);
        //另一方式
        Optional<Integer> reduce2 = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
        System.out.println(reduce2);
//    (8) 找到交易额最小的交易。
        Optional<Transaction> reduce3 = transactions.stream()
                .reduce((transaction1, transaction2) -> transaction1.getValue() < transaction2.getValue() ? transaction1 : transaction2);
        System.out.println(reduce3);
    }
}