package Lesson;

public class Employee {
    private int id;
    private String name;
    private String department;
    private String gender;
    private int age;
    private int salary;

    // No-argument constructor
    public Employee() {
    }

    // Constructor with id only
    public Employee(int id) {
        this.id = id;
    }

    // Constructor with id and name
    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Constructor with id, name, and department
    public Employee(int id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    // Constructor with id, name, department, and gender
    public Employee(int id, String name, String department, String gender) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.gender = gender;
    }

    // Constructor with id, name, department, gender, and age
    public Employee(int id, String name, String department, String gender, int age) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.gender = gender;
        this.age = age;
    }

    // Full constructor
    public Employee(int id, String name, String department, String gender, int age, int salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.gender = gender;
        this.age = age;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}