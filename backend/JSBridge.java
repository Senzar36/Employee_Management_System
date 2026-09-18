package Employee_Management_System;

public class JSBridge {
    private Employee_Management manager;

    public JSBridge(Employee_Management manager) {
        this.manager = manager;
    }

    public String searchEmployeeUI(int id) {
        for (Employee e : manager.empList) {
            if (e.id == id) {
                return String.format(
                    "{\"id\":%d, \"name\":\"%s\", \"age\":%d, \"department\":\"%s\", \"designation\":\"%s\", \"salary\":%.2f}",
                    e.id, e.name, e.age, e.department, e.designation, e.salary
                );
            }
        }
        return null;
    }

    public void updateEmployeeUI(int id, String name, int age, String dept, String desig, double salary) {
        for (Employee e : manager.empList) {
            if (e.id == id) {
                e.name = name;
                e.age = age;
                e.department = dept;
                e.designation = desig;
                e.salary = salary;
                manager.save_to_csv(); 
                return;
            }
        }
    }

    public void deleteEmployeeUI(int id) {
        if (manager.empList.removeIf(e -> e.id == id)) {
            manager.save_to_csv(); 
        }
    }

    public String getEmployeesJSON() {
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < manager.empList.size(); i++) {
            Employee e = manager.empList.get(i);
            json.append(String.format(
                "{\"id\":%d, \"name\":\"%s\", \"age\":%d, \"department\":\"%s\", \"designation\":\"%s\", \"salary\":%.2f}",
                e.id, e.name, e.age, e.department, e.designation, e.salary
            ));
            if (i < manager.empList.size() - 1) json.append(",");
        }
        json.append("]");
        return json.toString();
    }

    public void addEmployeeUI(String name, int id, String dept, String desig, double salary, int age) {
    Employee newEmp = new Employee(name, id, dept, desig, salary, age);
    
    manager.empList.add(newEmp);
    
    manager.save_to_csv(); 
    }
}