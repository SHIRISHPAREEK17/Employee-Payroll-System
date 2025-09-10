import java.util.ArrayList;

abstract class Employee {
    private String name;
    private int id;
    
    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }
    
    public abstract double calculateSalary(); 

    @Override
    public String toString() {
        return "Employee [name=" + name + ", id=" + id + ", salary=" + calculateSalary() + "]";
    }
}

class FullTimeEmployee extends Employee {
    private double monthlySalary;

    public FullTimeEmployee(String name, int id, double monthlySalary) {
        super(name, id);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double calculateSalary() {
        return monthlySalary;
    } 
}

class PartTimeEmployee extends Employee {
    private int hoursWorked;
    private double hourlyRate;

    public PartTimeEmployee(String name, int id, int hoursWorked, double hourlyRate) {
        super(name, id);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculateSalary() {
        return hoursWorked * hourlyRate;
    } 
}

class PayrollSystem {
    private ArrayList<Employee> employeeList;

    public PayrollSystem() {
        employeeList = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        for (Employee e : employeeList) {
            if (e.getId() == employee.getId()) {
                System.out.println("⚠ Employee with id " + employee.getId() + " already exists!");
                return;
            }
        }
        employeeList.add(employee);
    }

    public void removeEmployee(int id) {
        Employee employeeToRemove = null;

        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                employeeToRemove = employee;
                break;
            }
        }

        if (employeeToRemove != null) {
            employeeList.remove(employeeToRemove);  
            System.out.println("\nEmployee with id " + id + " removed.");
            displayEmployees();
        } else {
            System.out.println("No employee found with id " + id);
        }
    }

    public void displayEmployees() {
        System.out.println("\nCurrent Employees:");
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
    }

    public double calculateTotalPayroll() {
        double total = 0;
        for (Employee e : employeeList) {
            total += e.calculateSalary();
        }
        return total;
    }
}

public class Main {
    public static void main(String args[]) {
        PayrollSystem payrollSystem = new PayrollSystem();

        FullTimeEmployee emp1 = new FullTimeEmployee("Vikas", 1, 70000);
        PartTimeEmployee emp2 = new PartTimeEmployee("Ravi", 2, 70, 500);

        payrollSystem.addEmployee(emp1);
        payrollSystem.addEmployee(emp2); 

        payrollSystem.displayEmployees();

        System.out.println("\nTotal Payroll: " + payrollSystem.calculateTotalPayroll());

        payrollSystem.removeEmployee(2);

        System.out.println("\nTotal Payroll after removal: " + payrollSystem.calculateTotalPayroll());
    }
}
