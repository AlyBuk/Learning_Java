package Lesson;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        List<Integer> numbers = Arrays.asList(5,2,3,4,1);
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
    public static void markRatingV2(){
//        using parallel stream
        List<Integer> marks = Arrays.asList(45, 88, 67, 92, 55);
        IntSummaryStatistics stats = marks.parallelStream()
                .mapToInt(Integer::intValue)
                .summaryStatistics();

//        ptn --> summary returns IntSummaryStatistics Object
        System.out.println("Min: " + stats.getMin());
        System.out.println("Max: " + stats.getMax());
//        if we want to return array
        int [] result  = {stats.getMax(), stats.getMin()};
        System.out.println(Arrays.toString(result));

    }
    public static void reverseList(){
//        Reverse the order of the list.
        List<String> names = Arrays.asList("Ali", "Sara", "John", "Ahmed");
        Collections.reverse(names);
        for(String name : names){
            System.out.println(name);
        }

    }

    public static void shuffle(){
        List<String> cards = Arrays.asList("A", "K", "Q", "J");
        Collections.shuffle(cards);
        for(String card : cards){
            System.out.println(card);
        }
    }

    public static void swapElements(){
        List<String> fruits = Arrays.asList("Apple", "Banana", "Orange", "Mango");
        int apple = fruits.indexOf("Apple");
        Collections.swap(fruits,1,apple);
        fruits.forEach(System.out::println);

    }
    public static void findFrequency(){
        List<String> words = List.of(
                "Java", "SQL", "Java", "AWS", "SQL", "Java");
        Map<String, Long> collect = words.stream()
                .collect(Collectors.groupingBy(
                        c -> c,
                        Collectors.counting()
                ));
        System.out.println(collect);
    }

/** Group Strings by Length */

    public static void stringByLength(){
        List<String> words = List.of(
                "Java", "C", "Python", "Go", "HTML");
        Map<Integer, List<String>> collect = words.stream()
                .collect(Collectors.groupingBy(
                        String::length
                ));
        System.out.println(collect);
    }
    /** Group Employees by Department */
    public static void findByDepartment(){
        List<Employee> employees = List.of(
                new Employee(1, "Alice", "IT", "Female", 25, 60000),
                new Employee(2, "Bob", "HR", "Male", 30, 50000),
                new Employee(3, "Charlie", "Finance", "Male", 35, 70000),
                new Employee(4, "Diana", "IT", "Female", 28, 65000),
                new Employee(5, "Ethan", "Sales", "Male", 40, 55000),
                new Employee(6, "Fiona", "Marketing", "Female", 32, 62000),
                new Employee(7, "George", "IT", "Male", 29, 68000),
                new Employee(8, "Hannah", "Finance", "Female", 31, 72000),
                new Employee(9, "Ian", "HR", "Male", 27, 52000),
                new Employee(10, "Julia", "Sales", "Female", 26, 58000)
        );
        Map<String, List<Employee>> collect = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.mapping(
                                x -> new Employee(x.getId(), x.getName()),
                                Collectors.toList()
                        )
                ));
        collect.forEach((key, value) -> System.out.println(key + ": " + value));
    }

    public static void sumSalaryDepartment(){
        List<Employee> employees = List.of(
                new Employee(1, "Alice", "IT", "Female", 25, 60000),
                new Employee(2, "Bob", "HR", "Male", 30, 50000),
                new Employee(3, "Charlie", "Finance", "Male", 35, 70000),
                new Employee(4, "Diana", "IT", "Female", 28, 65000),
                new Employee(5, "Ethan", "Sales", "Male", 40, 55000),
                new Employee(6, "Fiona", "Marketing", "Female", 32, 62000),
                new Employee(7, "George", "IT", "Male", 29, 68000),
                new Employee(8, "Hannah", "Finance", "Female", 31, 72000),
                new Employee(9, "Ian", "HR", "Male", 27, 52000),
                new Employee(10, "Julia", "Sales", "Female", 26, 58000)
        );
        Map<String, Double> collect = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingInt(Employee::getSalary)
                ));
        collect.forEach((key, value) -> System.out.println(key + ": " + value));
    }

    /** Highest Paid Employee in Each Department */
    public static void highestPaidEmploye(){
        List<Employee> employees = List.of(
                new Employee(1, "Alice", "IT", "Female", 25, 60000),
                new Employee(2, "Bob", "HR", "Male", 30, 50000),
                new Employee(3, "Charlie", "Finance", "Male", 35, 70000),
                new Employee(4, "Diana", "IT", "Female", 28, 65000),
                new Employee(5, "Ethan", "Sales", "Male", 40, 55000),
                new Employee(6, "Fiona", "Marketing", "Female", 32, 62000),
                new Employee(7, "George", "IT", "Male", 29, 68000),
                new Employee(8, "Hannah", "Finance", "Female", 31, 72000),
                new Employee(9, "Ian", "HR", "Male", 27, 52000),
                new Employee(10, "Julia", "Sales", "Female", 26, 58000)
        );
        Map<String, Optional<Employee>> collect = employees.stream()
                .collect(
                        Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.maxBy(Comparator.comparingInt(Employee::getSalary))

                        )
                );
        collect.forEach((dept,emp) -> {
            Employee employee = emp.get();
            System.out.println(dept + ": " + employee.getName());
        });
    }

    /** Group Employees by First Letter */
    public static void groupByFirstLetter(){
        /** Expected Output
         * A -> [Alice,Adam]
         * B -> [Bob,Brian]
         * J -> [John,James]
         * */
        List<String> name = List.of(
                "John",
                "James",
                "Alice",
                "Adam",
                "Bob",
                "Brian"
        );
        Map<Character, List<String>> collect = name.stream().collect(
                Collectors.groupingBy(
                        x -> x.charAt(0)
                )
        );

        collect.forEach((key, value) -> System.out.println(key + ": " + value));
//        System.out.println(collect);
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
        System.out.println("=======V2=======");
        sortingV2();
        System.out.println("=======V3=======");
        sortingV3();
        System.out.println("=======Min/Max=======");
        marksRatings();
        System.out.println("=========Min/Max-V2=======");
        markRatingV2();
        System.out.println("===========ReverseList==========");
        reverseList();
        System.out.println("===========shuffle==========");
        shuffle();
        System.out.println("===========Swap Element==========");
        swapElements();
        System.out.println("=============Find Frequency=============");
        findFrequency();
        System.out.println("=============Find length=============");
        stringByLength();
        System.out.println("=============Find By Department=============");
        findByDepartment();
        System.out.println("=============Find By Salary Department=============");
        sumSalaryDepartment();
        System.out.println("=============Highest Salary By Department=============");
        highestPaidEmploye();
        System.out.println("=============Group by first Letter =============");
        groupByFirstLetter();

    }
}
