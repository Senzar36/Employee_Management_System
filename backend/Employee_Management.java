package Employee_Management_System;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

abstract class Employee_Management_Interface {
    public abstract void create_new_employee();
    public abstract void update_employee_details();
    public abstract void delete_employee();
    public abstract void save_to_csv();
    public abstract void load_from_csv();
    public abstract void display_details();
    public abstract void search_employee();
    public abstract void display_all_employees();
    public abstract void check_Employee_record_status();
}

public class Employee_Management extends Employee_Management_Interface {
    public ArrayList<Employee> empList = new ArrayList<>();
    private final String CSV_FILE = "employees.csv";

    public Employee_Management() {
        load_from_csv();
    }

    public void create_new_employee() {
        Scanner sc = new Scanner(System.in);
        System.out.println("How many employee data do you wish to store? ");
        if (!sc.hasNextInt()) 
            return;
        int count = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < count; i++) {
            System.out.println("Enter details of employee " + (i + 1));
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("ID: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Department: ");
            String department = sc.nextLine();
            System.out.print("Designation: ");
            String designation = sc.nextLine();
            System.out.print("Salary: ");
            double salary = sc.nextDouble();
            System.out.print("Age: ");
            int age = sc.nextInt();
            sc.nextLine();

            empList.add(new Employee(name, id, department, designation, salary, age));
        }
        save_to_csv();
        sc.close();
    }

    public void update_employee_details() {
        if (empList.isEmpty()) {
            System.out.println("No employee data is stored.");
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Employee e : empList) {
            if (e.id == id) {
                System.out.println("Which detail? (Name/Department/Designation/Salary/Age)");
                String choice = sc.nextLine();
                System.out.print("Enter new value: ");

                if (choice.equalsIgnoreCase("Name"))
                    e.name = sc.nextLine();
                else if (choice.equalsIgnoreCase("Department"))
                    e.department = sc.nextLine();
                else if (choice.equalsIgnoreCase("Designation"))
                    e.designation = sc.nextLine();
                else if (choice.equalsIgnoreCase("Salary")) {
                    e.salary = sc.nextDouble();
                    sc.nextLine();
                }
                else if (choice.equalsIgnoreCase("Age")) {
                    e.age = sc.nextInt();
                    sc.nextLine();
                }
                save_to_csv();
                sc.close();
                return;
            }
        }
        System.out.println("ID not found.");
    }

    public void delete_employee() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter ID to delete: ");
        int id = sc.nextInt();
        boolean removed = empList.removeIf(e -> e.id == id);
        if (removed) {
            System.out.println("Employee deleted successfully.");
            save_to_csv();
        } else {
            System.out.println("Employee ID not found.");
        }
        sc.close();
    }

    public void save_to_csv() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE, false))) {
            for (Employee e : empList) {
                writer.println(e.name + "," + e.id + "," + e.department + "," + e.designation + "," + e.salary + "," + e.age);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load_from_csv() {
        File file = new File(CSV_FILE);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] data = line.split(",");
                if (data.length == 6) {
                    // Added parsing safety to prevent NumberFormatException
                    empList.add(new Employee(
                        data[0], 
                        Integer.parseInt(data[1].trim()), 
                        data[2], 
                        data[3], 
                        Double.parseDouble(data[4].trim()), 
                        Integer.parseInt(data[5].trim())
                    ));
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading from CSV: " + e.getMessage());
        }
    }

    public void display_details() {
        if (empList.isEmpty()) {
            System.out.println("No records found.");
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter index (1 to " + empList.size() + "): ");
        int index = sc.nextInt() - 1;
        if (index >= 0 && index < empList.size()) {
            Employee e = empList.get(index);
            System.out.println("Name: " + e.name + ", ID: " + e.id);
        }
        sc.close();
    }

    public void search_employee() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter ID to search: ");
        int id = sc.nextInt();
        for (Employee e : empList) {
            if (e.id == id) {
                System.out.println("--Employee Found-- Name: " + e.name);
                return;
            }
        }
        System.out.println("Employee not found.");
        sc.close();
    }

    public void display_all_employees() {
        if (empList.isEmpty()) {
            System.out.println("No records.");
            return;
        }
        for (Employee e : empList) {
            System.out.println("ID: " + e.id + " | Name: " + e.name + " | Dept: " + e.department);
        }
    }

    public void check_Employee_record_status() {
        if (empList.isEmpty())
            System.out.println("No employee records exist.");
        else
            System.out.println("Total Records: " + empList.size());
    }
}