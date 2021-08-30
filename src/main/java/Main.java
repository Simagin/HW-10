package main.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Peter", "Ivan", "Olga", "Oleg", "Andrey", "Vasya");
        List<List<Integer>> listArr = Arrays.asList(Arrays.asList(1, 2, 0), Arrays.asList(4, 5));
        task_1(list);
        task_2(list);
        task_3(listArr);
        task_4();
        task_5();

    }

    public static void task_1(List<String> list) {
        List<String> collect1 = list.stream()
                .filter(name -> list.indexOf(name) % 2 != 0)
                .peek(name -> System.out.print(list.indexOf(name) + "." + name + " "))
                .collect(Collectors.toList());
    }

    public static void task_2(List<String> list) {
        List<String> collect1 = list.stream()
                .map(name -> name.toUpperCase())
                .sorted()
                .peek(name -> System.out.print(name + " "))
                .collect(Collectors.toList());
        //  System.out.println(collect1);
    }

    public static void task_3(List<List<Integer>> listArr) {

        List<Integer> collect = listArr.stream()
                .flatMap(integers -> integers.stream())
                .sorted()
                .collect(Collectors.toList());

        System.out.println(collect);
    }

    public static void task_4() {
        long a = 25214903917L;
        long c = 11L;
        long m = pow(2, 48);
        long seed = System.currentTimeMillis();
        Stream<Long> random = myRandom(a, c, m, seed);
        random.limit(10).forEach(System.out::println);
    }

    public static long pow(int value, int power) {
        if (power == 1) {
            return value;
        } else {
            return value * pow(value, power - 1);
        }
    }

    private static Stream<Long> myRandom(long a, long c, long m, long seed) {
        Stream<Long> iterate = Stream.iterate(seed, x -> (a * x + c) % m);
        return iterate;
    }

    public static void task_5(){
        Stream<Integer> stream1 = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8});
        Stream<Integer> stream2 = Arrays.stream(new Integer[]{9,10,11,12,13});
        Stream<Integer> newStream = zip(stream1, stream2);
        newStream.forEach(System.out::println);
    }

    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        final Iterator<T> iteratorFirst = first.iterator();
        final Iterator<T> iteratorSecond = second.iterator();
        ArrayList<T> arrayList = new ArrayList<T>();

        while (iteratorFirst.hasNext()&&iteratorSecond.hasNext()) {
            arrayList.add(iteratorFirst.next());
            arrayList.add(iteratorSecond.next());
        }
        return arrayList.stream();
    }

}
