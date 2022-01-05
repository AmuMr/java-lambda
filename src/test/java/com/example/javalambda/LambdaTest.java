package com.example.javalambda;

import com.example.javalambda.config.ThreadLocalTest;
import com.example.javalambda.converter.ConverterHandel;
import com.example.javalambda.entity.BaseDto;
import com.example.javalambda.entity.Data1;
import com.example.javalambda.entity.Fund;
import com.example.javalambda.entity.Menu;
import com.example.javalambda.entity.Person;
import com.example.javalambda.entity.Trader;
import com.example.javalambda.entity.Transaction;
import com.example.javalambda.entity.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.reducing;


public class LambdaTest {


    private boolean flag;

    private static void souts() {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println(i);
            }
        }).start();
    }

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            list1.add(i);
        }
        list1.stream().forEach(s -> {
            System.out.println(Thread.currentThread().getName() + ">>>>" + s);
        });
    }

    @Test
    public void test() {
        Consumer<String> str = (x) -> System.out.println(x);
        str.accept("hello");
    }

    @Test
    public void test1() {
        Supplier<Person> person = Person::new;

        System.out.println(person.get());
    }

    @Test
    public void test2() {
        Function<String, Person> function = (s) -> new Person();
        Person t = function.apply("t");
        System.out.println(t.getName());
    }

    @Test
    public void test3() {
        Function<String, String> str = x -> "F1 hello word :" + x;
        Function<String, String> str1 = x -> " F2 hello word :" + x;
        String liu = str1.compose(str).apply("liu");
        String tom = str1.andThen(str).apply("Tom");
        System.out.println(liu);
        System.out.println(tom);
    }

    @Test
    public void test4() {
        List<String> list = new ArrayList<String>() {{
            add("1");
            add("2");
            add("3");
            add("4");
            add("5");
        }};
        Map<String, String> collect = list.stream().collect(Collectors.toMap(s -> s, s -> s));

        System.out.println(collect);

        Stream<String> aa = Stream.of("aa", "bb");

    }

    @Test
    public void test5() {
        List<Fund> list = new ArrayList<Fund>() {{
            add(new Fund("1"));
            add(new Fund("2"));
            add(new Fund("3"));
            add(new Fund("4"));
        }};

        List<String> collect = list.stream().map(s -> s.getName()).collect(Collectors.toList());
        System.out.println(collect);

        Fund[] integers = list.toArray(new Fund[9]);

        System.out.println(integers);
    }

    @Test
    public void test6() {
        List<Double> list = new ArrayList<Double>() {{
            add(1.1);
            add(2.01);
            add(3.22);
            add(4.5);
            add(5.1);
        }};
        Double[] integers = list.toArray(new Double[1]);
        System.out.println(integers);
    }

    @Test
    public void test7() {
        List<Person> list = new ArrayList<Person>() {{
            add(new Person("tom", 10));
            add(new Person("sky", 20));
            add(new Person("jay", 30));
            add(new Person("tom", 35));
            add(new Person("tom", 15));
        }};

        long start = System.currentTimeMillis();
        List<Person> mergeList = new ArrayList<>();
        list.stream().collect(groupingBy(o -> (o.getName()), Collectors.toList())).forEach(
                (id, transfer) -> {
                    transfer.stream().reduce(
                            (a, b) -> new Person(a.getName(), Integer.parseInt(a.getVal()) + Integer.parseInt(b.getVal()))
                    ).ifPresent(mergeList::add);
                }

        );
        System.out.println(mergeList);
        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }

    @Test
    public void test8() {
        String str = "hellword";
        String[] split = str.split("");
        Stream.of(split).forEach(System.out::println);
    }

    @Test
    public void test9() {

        List<Person> list = new ArrayList<Person>() {{
            add(new Person("tom", 10));
            add(new Person("sky", 20));
            add(new Person("jay", 30));
            add(new Person("amu", 35));
        }};
        double count = list.stream().mapToDouble(s -> 1).sum();

        System.out.println(count);

    }

    @Test
    public void test10() {

        String str = "ABCDEFG";

        List<String> list = Arrays.asList(str.split(""));
        List<Person> collect = list.stream().map(s -> new Person()).collect(Collectors.toList());

        System.out.println(collect.toString());

    }

    @Test
    public void test11() {
        List<Fund> list = new ArrayList<Fund>() {{
            add(new Fund("a", 10));
            add(new Fund("b", 20));
            add(new Fund("c", 30));

        }};
        Double[] doubles = list.stream().map(Fund::getVal).toArray(Double[]::new);

        Supplier<StringBuilder> build = StringBuilder::new;
        StringBuilder append = build.get().append("a").append("b").append("c");
        System.out.println(append.toString());

    }

    @Test
    public void test12() {
        Stream<String> stream = Stream.of("1", "2", "3");
        String[] strings = stream.toArray(String[]::new);
    }

    @Test
    public void test13() {
        String s = RandomStringUtils.randomNumeric(20);
        System.out.println(s);
        String str = "你好";
        byte[] bytes = str.getBytes();
        System.out.println(Arrays.toString(bytes));
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();

    }

    @Test
    public void test14() {
        List<Person> list = new ArrayList<Person>() {{
            add(new Person("1", "tom", "1a", "10"));
            add(new Person("1", "tom", "1b", "11"));
            add(new Person("1", "tom", "1c", "12"));
            add(new Person("1", "jat", "1a", "13"));

            add(new Person("2", "sky", "1a", "14"));
            add(new Person("2", "sky", "1b", "15"));
            add(new Person("2", "liu", "1c", "16"));
            add(new Person("2", "liu", "1a", "17"));

        }};

        Map<String, List<Person>> collect = list.stream().collect(groupingBy(o -> (o.getId() + o.getName())));

        collect.forEach((k, v) -> System.out.println(k + ">>" + v));

    }

    @Test
    public void test15() {
        IntStream range = IntStream.range(0, 100);
        Stream<Integer> boxed = range.boxed();
        boxed.forEach(System.out::println);
    }

    @Test
    public void test16() {
        IntStream.range(0, 10).forEach(i -> System.out.println(i));
        IntStream.rangeClosed(0, 10).forEach(i -> System.out.println(i));
    }

    @Test
    public void test17() {
        List<Integer> list = new ArrayList<>();
        IntStream.range(0, 100).forEach(list::add);
        list.parallelStream().forEach(this::sout);

    }

    public void sout(int i) {
        System.out.println(i);
    }

    @Test
    public void test18() {
        List<Fund> list = new ArrayList<Fund>() {{
            add(new Fund("a", 10));
            add(new Fund("b", 20));
            add(new Fund("c", 30));
            add(new Fund("c", 40));

        }};

        Map<String, Double> map = new HashMap<>();
        // list.forEach(s -> map.computeIfAbsent(s.getName(), key ->  1+1.0));
        list.forEach(s -> {
            map.computeIfPresent(s.getName(), (key, value) -> {
                if (map.containsKey(s.getName())) {
                    return s.getVal() + value;
                } else {
                    return map.put(s.getName(), s.getVal());
                }
            });
        });

        System.out.println(map);

    }

    @Test
    public void test19() {
        //java8 实现计算功能统计字符串出现次数
        Map<String, AtomicInteger> countMap = new HashMap<>();
        List<String> source = Arrays.asList("hello", "world", "hello", "welcome", "hello", "hello", "welcome", "simon");
        for (String s : source) {
            countMap.computeIfAbsent(s, key -> new AtomicInteger()).getAndIncrement();
        }
        System.out.println(countMap);
    }

    @Test
    public void test20() {
        int andIncrement = new AtomicInteger().getAndIncrement();
        System.out.println(andIncrement);
    }

    @Test
    public void test21() {
        //冒泡排序
        int[] numbers = new int[]{1, 5, 8, 2, 3, 9, 4};

        for (int i = 0; i < numbers.length - 1; i++) {

            for (int j = 0; j < numbers.length - 1 - i; j++) {

                if (numbers[j] > numbers[j + 1]) {

                    int temp = numbers[j + 1];
                    numbers[j + 1] = numbers[j];
                    numbers[j] = temp;
                }
                System.out.println(Arrays.toString(numbers));

            }

        }
    }

    @Test
    public void test22() {
        //选择排序
        int[] numbers = new int[]{1, 5, 8, 2, 3, 9, 4};
        for (int i = 0; i < numbers.length; i++) {
            int k = i;
            for (int j = k + 1; j < numbers.length; j++) {
                if (numbers[j] < numbers[k]) {
                    k = j;
                }
            }
            if (i != k) {
                int temp = numbers[i];
                numbers[i] = numbers[k];
                numbers[k] = temp;
            }
        }
        System.out.println(Arrays.toString(numbers));

    }

    @Test
    public void test23() throws Exception {
        File fileName = new File("/Users/sky/Downloads/123.txt");
        RandomAccessFile file = new RandomAccessFile(fileName, "rw");

        for (int i = 0; i < 100000; i++) {
            String str = "第" + i + "行数据:TrendForce最新报告预测，苹果2021年iPhone中的A15芯片将坚持使用5nm工艺，但会转向增强型的“5nm+”版本\r";
            file.write(str.getBytes());
        }
        file.close();

        StopWatch watch = new StopWatch("test");
        watch.start();
        Stream<String> stream = Files.lines(Paths.get(fileName.getPath()));
        RandomAccessFile newFile = new RandomAccessFile("/Users/sky/Downloads/456.txt", "rw");

        stream.forEach(s -> {
            try {
                newFile.write(s.getBytes());
                newFile.write("\r".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        newFile.close();
        watch.stop();
        System.out.println(watch.getTime(TimeUnit.SECONDS));
    }

    @Test
    public void test24() {
        Map<Integer, Integer> map = new TreeMap<>();
        map.put(2, 5);
        map.put(1, 3);
        map.put(3, 6);
        map.put(7, 9);
        map.put(-3, -1);

        Map.Entry<Integer, Integer> mergeMap = map.entrySet().stream().findFirst().get();

        Integer max = map.values().stream().sorted(Comparator.reverseOrder()).findFirst().get();
        mergeMap.setValue(max);

        System.out.println(mergeMap);

    }

    @Test
    public void test25() {
        LocalTime now = LocalTime.now();
        System.out.println(now);
    }

    @Test
    public void test26() {
        String reduce = Stream.of("A", "B", "C", "D").collect(joining("<1D>"));
        System.out.println(reduce);

        String pad = StringUtils.leftPad("啊", 10, "a");
        System.out.println(pad);

    }

    @Test
    public void test27() {
        convert("a", s -> deal(s));
    }

    private void convert(String str, Consumer<String> consumer) {
        for (int i = 0; i < 3; i++) {
            consumer.accept(str);

        }
    }

    private void deal(String s) {
        System.out.println(s);
    }

    @Test
    public void test28() {
        List<Fund> list = new ArrayList<Fund>() {{
            add(new Fund("a", 10));
            add(new Fund("b", 20));
            add(new Fund("c", 30));
            add(new Fund("d", 40));

        }};

        List<Fund> collect = list.stream().map(s -> {
            s.setVal(s.getVal() + 1);
            return s;
        }).collect(Collectors.toList());

        collect.forEach(System.out::println);

    }

    @Test
    public void test29() {
        List<Fund> list = new ArrayList<Fund>() {{
            add(new Fund("b", 20));
            add(new Fund("d", 40));
            add(new Fund("a", 10));
            add(new Fund("c", 30));

        }};
        list.sort(comparing(Fund::getVal).reversed());
        System.out.println(list);

    }

    @Test
    public void test30() {
        Consumer<Fund> c1 = s -> s.setVal(s.getVal() + 1);
        Consumer<Fund> c2 = s -> s.setVal(s.getVal() * 2);
        Consumer<Fund> h = c1.andThen(c2);

        Fund ss = new Fund("ss", 1);
        h.accept(ss);

        System.out.println(ss);


    }

    @Test
    public void test31() {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        list.parallelStream().forEach(System.out::println);

    }

    @Test
    public void test32() {
        List<String> strings = Arrays.asList("hello", "world");

        List<String[]> list = strings.stream().map(s -> s.split("")).distinct().collect(Collectors.toList());
        list.forEach(s -> System.out.println(Arrays.toString(s)));


        List<String> collect = strings.stream().map(s -> s.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
        System.out.println(collect);

    }

    @Test
    public void test33() {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            list.add(i);
        }


        boolean b = list.stream().anyMatch(s -> s == 1);
        System.out.println(b);
        boolean b1 = list.stream().allMatch(s -> s < 21);
        System.out.println(b1);
        boolean b2 = list.stream().noneMatch(s -> s > 21);
        System.out.println(b2);
        Optional<Integer> any = list.stream().filter(s -> s < 10 && s > 5).findAny();
        System.out.println(any.get());
    }

    @Test
    public void test34() {
        List<Integer> list = new ArrayList<>();

        for (int i = 1; i < 10; i++) {
            list.add(i);
        }

        Integer reduce = list.stream().reduce(0, (a, b) -> a + b);
        System.out.println(reduce);

        Integer reduce1 = list.stream().reduce(1, (a, b) -> a * b);
        System.out.println(reduce1);

        Integer reduce2 = list.stream().reduce(0, Integer::sum);
        System.out.println(reduce2);

    }

    @Test
    public void test35() {
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


        List<Transaction> collect = transactions.stream().filter(s -> s.getYear() == 2011).sorted(comparing(Transaction::getValue)).collect(Collectors.toList());
        System.out.println(collect);
//
        List<String> collect1 = transactions.stream().map(s -> s.getTrader().getCity()).distinct().collect(Collectors.toList());
        collect1.forEach(s1 -> System.out.println(s1));
//
        List<Transaction> collect2 = transactions.stream().filter(s -> s.getTrader().getCity().equals("Cambridge")).sorted(comparing(s -> s.getTrader().getName())).collect(Collectors.toList());
        collect2.forEach(s -> System.out.println(s));
//
        String collect3 = transactions.stream().map(s -> s.getTrader().getName()).sorted().distinct().collect(joining());
        System.out.println(collect3);
//
        boolean anyMatch = transactions.stream().anyMatch(s -> s.getTrader().getCity().equals("Milan"));
        System.out.println(anyMatch);
//
        Optional<Integer> count = transactions.stream().filter(s -> s.getTrader().getCity().equals("Cambridge")).map(Transaction::getValue).reduce(Integer::sum);
        System.out.println(count.get());
//
        Optional<Integer> reduce = transactions.stream().map(Transaction::getValue).reduce(Integer::max);
        System.out.println(reduce.get());
//
        Optional<Integer> max = transactions.stream().map(Transaction::getValue).max(Integer::compareTo);
        System.out.println(max.get());
//
        Optional<Integer> max1 = transactions.stream().map(Transaction::getValue).max(Comparator.comparing(Integer::intValue));
        System.out.println(max1.get());
//
        Optional<Integer> max2 = transactions.stream().map(Transaction::getValue).min(Comparator.comparing(Integer::intValue));
        System.out.println(max2.get());
//
        Optional<Transaction> min = transactions.stream().min(comparing(Transaction::getValue));
        System.out.println(min.get().getValue());

        Optional<Transaction> reduce1 = transactions.stream().reduce((a1, a2) -> a1);

        System.out.println(reduce1.get().getValue());

    }

    @Test
    public void test36() {
        IntStream intStream = IntStream.rangeClosed(0, 20);//包含后面
        Stream<Integer> boxed = intStream.boxed();
        //boxed.forEach(System.out::println);

        IntStream intStream1 = IntStream.range(0, 20); //不包含
        //intStream1.forEach(System.out::println);
        Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);

    }

    @Test
    public void test37() {
        Consumer consumer = getConsumer();

        Consumer consumer1 = s -> System.out.println("consumer1" + s);

        Consumer consumer2 = s -> System.out.println("consumer2" + s);

        Consumer consumer3 = consumer2.andThen(consumer1).andThen(consumer);

        consumer3.accept("a");

    }

    private Consumer<String> getConsumer() {
        return s -> {
            s = s.toUpperCase();
            int i = 10 / 0;
            System.out.println("consumer" + s);
        };
    }

    @Test
    public void test38() {

        List<Fund> list1 = new ArrayList<Fund>() {{
            add(new Fund("a", 10));
            add(new Fund("b", 20));

        }};

        List<Fund> list2 = new ArrayList<Fund>() {{
            add(new Fund("c", 30));
            add(new Fund("d", 40));

        }};

        Stream<Fund> concat = Stream.concat(list1.stream(), list2.stream());
        //concat.forEach(System.out::println);

        //  DoubleSummaryStatistics statistics = concat.collect(summarizingDouble(Fund::getVal));

        // System.out.println(statistics.getMax());
        Map<String, List<Fund>> collect = concat.collect(groupingBy(Fund::getName));
        System.out.println(collect);


    }

    @Test
    public void test39() {

        List<Data1> list1 = new ArrayList<Data1>() {{
            add(new Data1(1, "tom", 10));
            add(new Data1(2, "jay", 20));
            add(new Data1(3, "sky", 30));
            add(new Data1(4, "amu", 40));

        }};

        String collect = list1.stream().map(Data1::getName).collect(joining(","));
        System.out.println(collect);

        Integer integer = list1.stream().map(Data1::getAmount).collect(reducing(0, (a, b) -> a + b));
        System.out.println(integer);

        Integer integer1 = list1.stream().collect(reducing(1, Data1::getAmount, (a, b) -> a * b));
        System.out.println(integer1);

        List<String> list2 = new ArrayList<String>();
        System.out.println(list2.contains("1"));
    }

    @Test
    public void test40() {
        List<Data1> list1 = new ArrayList<Data1>() {{
            add(new Data1(1, "tom", 10));
            add(new Data1(2, "jay", 20));
            add(new Data1(3, "sky", 30));
            add(new Data1(4, "amu", 40));

        }};

        List<Data1> list2 = new ArrayList<Data1>() {{
            add(new Data1(1, "tom", 10));
            add(new Data1(2, "jay", 20));
        }};

        list1.addAll(list2);

    }

    @Test
    public void test41() {
        List<Integer> list1 = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            list1.add(i);
        }
        list1.stream().forEach(s -> {
            System.out.println(Thread.currentThread().getName() + ">>>>" + s);
        });
    }

    @Test
    public void test42() throws UnsupportedEncodingException {
        Object invoke = new Object();
        String xml = (String) invoke;
        new String(xml.getBytes("GBK"), "UTF-8");
    }

    @Test
    public void test43() {
        List<Data1> list1 = new ArrayList<Data1>() {{
            add(new Data1(1, "tom", 10));
            add(new Data1(2, "jay", 20));
            add(new Data1(3, "sky", 30));
            add(new Data1(4, "amu", 40));

        }};

        Comparator<Data1> comparator = Comparator.comparingInt(Data1::getId);

        Optional<Data1> result = list1.stream().collect(maxBy(comparator));
        System.out.println(result);

        IntSummaryStatistics summaryStatistics = list1.stream().collect(Collectors.summarizingInt(Data1::getId));
        System.out.println(summaryStatistics.getMin());
    }

    @Test
    public void test44() {
        List<Data1> list = new ArrayList<Data1>() {{
            add(new Data1(1, "tom", 10));
            add(new Data1(2, "jay", 20));
            add(new Data1(3, "sky", 30));
            add(new Data1(4, "amu", 40));
        }};

        Integer integer = list.stream().collect(reducing(0, Data1::getAmount, Integer::sum));
        System.out.println(integer);
        Integer val = list.stream().map(Data1::getAmount).reduce(Integer::sum).get();
        System.out.println(val);

        String names = list.stream().map(Data1::getName).collect(joining(","));
        System.out.println(names);

        String names1 = list.stream().collect(reducing("", Data1::getName, (s1, s2) -> s1 + "-" + s2));
        System.out.println(names1);
    }

    @Test
    public void test45() {
        List<Menu> list = new ArrayList<Menu>() {{
            add(new Menu(true, "tudousi"));
            add(new Menu(true, "xihongshi"));
            add(new Menu(false, "yu"));
            add(new Menu(false, "rou"));
        }};
        Map<Boolean, List<Menu>> collect = list.stream().collect(partitioningBy(Menu::isVegetarian));
        System.out.println(collect);

        List<Menu> menus = list.stream().filter(Menu::isVegetarian).collect(Collectors.toList());
        System.out.println(menus);

        Map<Boolean, Long> collect1 = list.stream().collect(partitioningBy(Menu::isVegetarian, counting()));
        System.out.println(collect1);
    }

    @Test
    public void test46() {
        ConverterHandel<String, User> converterHandel = (s -> {
            User user = new User();
            user.setName(s);
            user.setSex("1");
            user.setAge(16);
            return user;
        });
        BaseDto dto = converterHandel.convert("tom");

    }

    @Test
    public void test47() {
        String numeric = RandomStringUtils.randomNumeric(10);
        ThreadLocalTest.set(numeric);
        test48();
    }

    public void test48() {
        test49();

    }

    public void test49() {
        test50();

    }

    public void test50() {

        test51();
    }

    public void test51() {
        System.out.println(ThreadLocalTest.get());
    }

    @Test
    public void test52() {

        String str= "";
        StringJoiner joiner = new StringJoiner(",");
        for (int i = 0; i < 10; i++) {
             joiner.add(i+"");
        }
        System.out.println(joiner.toString());
    }


}
