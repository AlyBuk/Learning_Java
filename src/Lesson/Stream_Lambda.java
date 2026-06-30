package Lesson;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Stream_Lambda {
    public static void sortingVal(){
        // sort the values in ascending order
        // print result

        List<Integer> numbers = Arrays.asList(5, 2, 9, 1, 7);
//        numbers.sort((a,b) -> a.compareTo(b));
        numbers.sort(Integer::compareTo);
        numbers.forEach(System.out::println);
    }

    public static void sortingV2(){
        List<Integer> numbers = Arrays.asList(1,2,3,4,5);
        numbers.sort((a,b) -> b.compareTo(a));

        numbers.forEach(System.out::println);
    }

    public static void sortingV3(){
        List<Integer> numbers = Arrays.asList(1,2,3,4,5);
        Collections.sort(numbers,Collections.reverseOrder());
        numbers.forEach(System.out::println);
    }
    public static void marksRatings(){
//        Print the highest mark.
//        Print the lowest mark.
        List<Integer> marks = Arrays.asList(45, 88, 67, 92, 55);
        int[] collect = marks.stream()
                .collect(Collectors.teeing(
                        Collectors.minBy(Integer::compareTo),
                        Collectors.maxBy(Integer::compareTo),
                        (min, max) -> new int[]{
                                min.orElse(0),
                                max.orElse(0)
                        }
                ));
        System.out.println(Arrays.toString(collect));
    }


    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Alice", 25.0, "New York"),
                new Person("Bob", 30.0, "London"),
                new Person("Charlie", 22.0, "New York"),
                new Person("Diana", 28.0, "Paris"),
                new Person("Eve", 35.0, "London"),
                new Person("Frank", 40.0, "New York"),
                new Person("Grace", 27.0, "Paris"),
                new Person("Henry", 33.0, "Berlin"),
                new Person("Ivy", 29.0, "Berlin"),
                new Person("Jack", 45.0, "London")
        );

        Map<String, Double> collect = people.stream()
                .collect(Collectors.groupingBy(
                        Person::city,
                        Collectors.averagingDouble(Person::age)
                ));
        collect.forEach((city, age) ->  System.out.printf("%s: %.2f%n", city, age));

        sortingVal();
        System.out.println("==============");
        sortingV2();
        System.out.println("==============");
        sortingV3();
        System.out.println("==============");
        marksRatings();
    }
}
