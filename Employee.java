package Employee_Management_System;

public class Employee {
     String name;
     int id;
     String department;
     String designation;
     double salary;
     int age;

    public Employee(String name, int id, String department, String designation, double salary, int age) {
        this.name = name;
        this.id = id;
        this.department = department;
        this.designation = designation;
        this.salary = salary;
        this.age = age;
    }

    @Override
    public String toString() {
        return "\nName: " + name + "\nID: " + id + "\nDepartment: " + department +
                "\nDesignation: " + designation + "\nSalary: " + salary + "\nAge: " + age;
    }
}