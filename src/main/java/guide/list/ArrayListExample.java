package guide.list;

import guide.common.api.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ArrayListExample {
    private final static int SIZE = 20;

    public static void main(String[] args) {

        // Create an ArrayList to store Employee objects.
        // Always Set initialCapacity to avoid reallocation
        // The ArrayList class, by default, starts with an initial capacity of 10.
        // As elements are added, if the size exceeds the capacity, the ArrayList resizes (usually by doubling its capacity).
        List<Employee> employeeList = new ArrayList<>(SIZE);

        // Add employees to the ArrayList.
        System.out.println("Adding Employees...");
        addEmployees(employeeList);

        // Display all employees.
        System.out.println("\nAll Employees:");
        displayEmployees(employeeList);

        // Access an employee by index.
        System.out.println("\nAccessing Employee at index 2:");
        accessEmployee(employeeList, 2);

        // Update an employee at a specific index.
        System.out.println("\nUpdating Employee at index 1:");
        updateEmployee(employeeList, 1, new Employee(UUID.randomUUID(), "Updated Employee"));

        // Remove an employee by index.
        System.out.println("\nRemoving Employee at index 0:");
        removeEmployee(employeeList, 0);

        // Search for an employee in the list.
        System.out.println("\nSearching for an Employee:");
        searchEmployee(employeeList, "Employee_3");
    }

    /**
     * Searches for an employee by name.
     * Time Complexity: O(n) - Iterates through the list to find the matching employee.
     * Space Complexity: O(1) - No additional data structures are used.
     */
    private static void searchEmployee(List<Employee> employeeList, final String name) {
        for (Employee employee : employeeList) {
            if (employee.getName().equals(name)) {
                System.out.println("Found: " + employee);
                return;
            }
        }
        System.out.println("Employee with name '" + name + "' not found.");
    }

    /**
     * Removes an employee from the list by index.
     * Time Complexity: O(n) - Removing an element shifts all subsequent elements.
     * Space Complexity: O(1) - No additional data structures are used.
     */
    private static void removeEmployee(List<Employee> employeeList, int index) {
        if (index >= 0 && index < employeeList.size()) {
            Employee removedEmployee = employeeList.remove(index);
            System.out.println("Removed: " + removedEmployee);
        } else {
            System.out.println("Invalid index.");
        }
    }

    /**
     * Updates an employee at a specific index.
     * Time Complexity: O(1) - Replacing an element at a specific index is constant time.
     * Space Complexity: O(1) - No additional data structures are used.
     */
    private static void updateEmployee(List<Employee> employeeList, int index, Employee updatedEmployee) {
        if (index >= 0 && index < employeeList.size()) {
            employeeList.set(index, updatedEmployee);
            System.out.println("Updated: " + updatedEmployee);
        } else {
            System.out.println("Invalid index.");
        }
    }

    /**
     * Accesses an employee at a specific index.
     * Time Complexity: O(1) - Accessing an element by index is constant time.
     * Space Complexity: O(1) - No additional data structures are used.
     */
    private static void accessEmployee(List<Employee> employeeList, int index) {
        if (index >= 0 && index < employeeList.size()) {
            Employee employee = employeeList.get(index);
            System.out.println("Accessed: " + employee);
        } else {
            System.out.println("Invalid index.");
        }
    }

    /**
     * Displays all employees in the list.
     * Time Complexity: O(n) - Iterates through all elements in the list.
     * Space Complexity: O(1) - No additional data structures are used.
     */
    private static void displayEmployees(List<Employee> employeeList) {
        employeeList.forEach(System.out::println);
    }

    /**
     * Adds 5 employees to the list.
     * Time Complexity: O(1) per addition (amortized), O(n) total for n additions.
     *     - Adding an element to an ArrayList is O(1) on average unless resizing occurs.
     * Space Complexity: O(n) - Space required to store n elements in the list.
     */
    private static void addEmployees(List<Employee> employeeList) {
        for (int i = 0; i < SIZE; i++) {
            Employee employee = Employee.builder(UUID.randomUUID()).name("Employee_" + i).build();
            employeeList.add(employee);
            System.out.println("Added: " + employee);
        }
    }
}