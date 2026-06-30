# Java 8+ Streams & Lambda API Learning Guide

> A practical guide to mastering Java Streams, Lambda Expressions, Method References, Functional Interfaces, and Collectors.

---

# Table of Contents

1. Introduction
2. Lambda Expressions
3. Functional Interfaces
4. Method References
5. Stream API
6. Intermediate Operations
7. Terminal Operations
8. Collectors
9. Optional
10. Parallel Streams
11. Advanced Stream Examples
12. Best Practices
13. Interview Questions
14. Cheat Sheet

---

# 1. Introduction

Java 8 introduced functional programming features including:

- Lambda Expressions
- Stream API
- Functional Interfaces
- Method References
- Optional
- Collectors
- Parallel Streams

These features make code:

- Cleaner
- More readable
- Less boilerplate
- Easier to process collections

---

# Before Java 8

```java
List<String> names = Arrays.asList("John", "Alice", "Bob");

for(String name : names){
    System.out.println(name);
}
```

After Java 8

```java
names.forEach(name -> System.out.println(name));
```

Or even shorter

```java
names.forEach(System.out::println);
```

---

# 2. Lambda Expressions

## Syntax

```java
(parameters) -> expression
```

or

```java
(parameters) -> {
    statements
}
```

Examples

### No parameter

```java
() -> System.out.println("Hello");
```

### One parameter

```java
x -> x * 2
```

### Multiple parameters

```java
(a, b) -> a + b
```

### Block body

```java
(a, b) -> {
    int sum = a + b;
    return sum;
}
```

---

# Lambda Examples

Sort List

```java
Collections.sort(list,
    (a, b) -> a.compareTo(b));
```

Runnable

```java
Runnable r = () -> System.out.println("Running");
```

Thread

```java
new Thread(() ->
    System.out.println("Hello")
).start();
```

---

# 3. Functional Interfaces

A functional interface has exactly one abstract method.

Examples:

- Runnable
- Callable
- Comparator
- Predicate
- Consumer
- Supplier
- Function

Create your own

```java
@FunctionalInterface
interface Calculator{
    int calculate(int a, int b);
}
```

Use

```java
Calculator add = (a,b)->a+b;

System.out.println(add.calculate(5,3));
```

---

# Built-in Functional Interfaces

## Predicate<T>

Returns boolean

```java
Predicate<Integer> even =
    x -> x % 2 == 0;

System.out.println(even.test(10));
```

Methods

```
test()
and()
or()
negate()
```

---

## Consumer<T>

Consumes data

```java
Consumer<String> print =
    System.out::println;

print.accept("Hello");
```

---

## Supplier<T>

Produces data

```java
Supplier<Double> random =
    Math::random;

System.out.println(random.get());
```

---

## Function<T,R>

Transforms value

```java
Function<String,Integer> length =
    String::length;

System.out.println(length.apply("Java"));
```

---

## UnaryOperator

```java
UnaryOperator<Integer> square =
    x -> x*x;
```

---

## BinaryOperator

```java
BinaryOperator<Integer> add =
    Integer::sum;
```

---

# 4. Method References

Instead of

```java
name -> System.out.println(name)
```

Use

```java
System.out::println
```

Types

## Static Method

```java
Integer::parseInt
```

## Instance Method

```java
String::toUpperCase
```

## Object Method

```java
obj::display
```

## Constructor

```java
ArrayList::new
```

---

# 5. Stream API

A Stream processes collections efficiently.

Collection

↓

Stream

↓

Operations

↓

Result

Create Stream

```java
List<Integer> list =
Arrays.asList(1,2,3,4);

Stream<Integer> stream =
list.stream();
```

---

# Stream Pipeline

```text
Collection

↓

stream()

↓

Intermediate Operations

↓

Terminal Operation
```

Example

```java
list.stream()
    .filter(x->x>5)
    .map(x->x*2)
    .forEach(System.out::println);
```

---

# 6. Intermediate Operations

These return another Stream.

## filter()

```java
numbers.stream()
.filter(x -> x % 2 == 0)
.forEach(System.out::println);
```

---

## map()

```java
names.stream()
.map(String::toUpperCase)
.forEach(System.out::println);
```

---

## flatMap()

Nested Lists

```java
List<List<Integer>> list =
Arrays.asList(
    Arrays.asList(1,2),
    Arrays.asList(3,4)
);

list.stream()
.flatMap(Collection::stream)
.forEach(System.out::println);
```

Output

```
1
2
3
4
```

---

## distinct()

```java
list.stream()
.distinct()
.forEach(System.out::println);
```

---

## sorted()

```java
list.stream()
.sorted()
.forEach(System.out::println);
```

Reverse

```java
.sorted(Comparator.reverseOrder())
```

---

## peek()

Useful for debugging

```java
list.stream()
.peek(System.out::println)
.collect(Collectors.toList());
```

---

## limit()

```java
stream.limit(5);
```

---

## skip()

```java
stream.skip(2);
```

---

# 7. Terminal Operations

## forEach()

```java
stream.forEach(System.out::println);
```

---

## collect()

```java
List<String> result =
stream.collect(Collectors.toList());
```

---

## count()

```java
long count =
stream.count();
```

---

## reduce()

Sum

```java
int sum = list.stream()
.reduce(0, Integer::sum);
```

Product

```java
int product = list.stream()
.reduce(1, (a,b)->a*b);
```

---

## min()

```java
list.stream()
.min(Integer::compare);
```

---

## max()

```java
list.stream()
.max(Integer::compare);
```

---

## findFirst()

```java
stream.findFirst();
```

---

## findAny()

```java
stream.findAny();
```

---

## anyMatch()

```java
stream.anyMatch(x->x>100);
```

---

## allMatch()

```java
stream.allMatch(x->x>0);
```

---

## noneMatch()

```java
stream.noneMatch(x->x<0);
```

---

# 8. Collectors

## toList()

```java
.collect(Collectors.toList())
```

---

## toSet()

```java
.collect(Collectors.toSet())
```

---

## joining()

```java
names.stream()
.collect(Collectors.joining(", "));
```

Output

```
John, Alice, Bob
```

---

## counting()

```java
Collectors.counting()
```

---

## averagingInt()

```java
Collectors.averagingInt(Employee::getSalary)
```

---

## summingInt()

```java
Collectors.summingInt(Employee::getSalary)
```

---

## groupingBy()

```java
employees.stream()
.collect(Collectors.groupingBy(
Employee::getDepartment));
```

---

## partitioningBy()

```java
Collectors.partitioningBy(
e->e.getSalary()>50000);
```

---

## mapping()

```java
Collectors.mapping(
Employee::getName,
Collectors.toList()
)
```

---

## collectingAndThen()

```java
Collectors.collectingAndThen(
Collectors.toList(),
Collections::unmodifiableList
)
```

---

# 9. Optional

Avoid NullPointerException.

Create

```java
Optional<String> name =
Optional.of("Java");
```

Nullable

```java
Optional.ofNullable(value);
```

Empty

```java
Optional.empty();
```

Check

```java
isPresent()
```

Get

```java
get()
```

Better

```java
orElse("Unknown")
```

or

```java
orElseGet(() -> "Generated")
```

Throw

```java
orElseThrow()
```

Map

```java
optional.map(String::toUpperCase)
```

---

# 10. Parallel Streams

Sequential

```java
list.stream()
```

Parallel

```java
list.parallelStream()
```

Example

```java
list.parallelStream()
.forEach(System.out::println);
```

Use only for:

- Large datasets
- CPU-intensive work

Avoid for:

- Small collections
- Database operations
- File I/O

---

# 11. Advanced Examples

## Find Highest Salary

```java
employees.stream()
.max(Comparator.comparing(Employee::getSalary));
```

---

## Group by Department

```java
employees.stream()
.collect(Collectors.groupingBy(
Employee::getDepartment));
```

---

## Count Employees

```java
employees.stream()
.collect(Collectors.counting());
```

---

## Average Salary

```java
employees.stream()
.collect(Collectors.averagingInt(
Employee::getSalary));
```

---

## Convert to Map

```java
employees.stream()
.collect(Collectors.toMap(
Employee::getId,
Employee::getName
));
```

---

## Remove Duplicates

```java
list.stream()
.distinct()
.collect(Collectors.toList());
```

---

## Sort by Salary

```java
employees.stream()
.sorted(Comparator.comparing(
Employee::getSalary))
.collect(Collectors.toList());
```

---

## Top 3 Salaries

```java
employees.stream()
.sorted(
Comparator.comparing(Employee::getSalary)
.reversed()
)
.limit(3)
.collect(Collectors.toList());
```

---

## Names in Uppercase

```java
employees.stream()
.map(Employee::getName)
.map(String::toUpperCase)
.collect(Collectors.toList());
```

---

# 12. Best Practices

✔ Prefer method references when possible.

```java
System.out::println
```

✔ Keep streams readable.

✔ Avoid modifying source collections.

✔ Use Optional instead of null.

✔ Use parallel streams only when beneficial.

✔ Prefer immutable collections.

✔ Keep lambdas small.

✔ Don't overuse streams for simple loops.

---

# 13. Common Interview Questions

### Q1. Difference between map() and flatMap()

| map | flatMap |
|------|----------|
| One-to-one | One-to-many |
| Returns Stream<T> | Flattens nested streams |

---

### Q2. Difference between findAny() and findFirst()

| findFirst | findAny |
|------------|----------|
| Ordered | May return any element |
| Slower | Faster in parallel |

---

### Q3. Difference between Collection and Stream

| Collection | Stream |
|------------|---------|
| Stores data | Processes data |
| Reusable | Single-use |
| Eager | Lazy |

---

### Q4. Why Streams are Lazy?

Intermediate operations execute only when a terminal operation is invoked.

---

### Q5. What is reduce()?

Combines stream elements into a single result.

Example

```java
int sum = list.stream()
.reduce(0, Integer::sum);
```

---

### Q6. Difference between forEach() and peek()

| forEach | peek |
|----------|------|
| Terminal | Intermediate |
| Consumes stream | Mainly for debugging |

---

# 14. Cheat Sheet

## Stream Creation

```java
list.stream()
Stream.of(...)
Arrays.stream(...)
```

## Intermediate Operations

```
filter()
map()
flatMap()
sorted()
distinct()
peek()
skip()
limit()
```

## Terminal Operations

```
collect()
count()
reduce()
forEach()
min()
max()
findFirst()
findAny()
allMatch()
anyMatch()
noneMatch()
```

## Collectors

```
toList()
toSet()
toMap()
joining()
groupingBy()
partitioningBy()
counting()
mapping()
summingInt()
averagingInt()
collectingAndThen()
```

## Functional Interfaces

```
Predicate
Consumer
Supplier
Function
UnaryOperator
BinaryOperator
Comparator
Runnable
Callable
```

## Method References

```
Class::staticMethod
Class::instanceMethod
object::instanceMethod
Class::new
```

---

# Learning Roadmap

1. Learn Lambda Expressions
2. Understand Functional Interfaces
3. Practice Method References
4. Learn Stream Creation
5. Master Intermediate Operations
6. Master Terminal Operations
7. Learn Collectors
8. Understand Optional
9. Practice Parallel Streams
10. Solve 100+ Stream-based coding problems

---
