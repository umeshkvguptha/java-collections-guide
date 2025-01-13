package guide.map;

import guide.common.api.Employee;

import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

public class TreeMapExample {
    private final static int SIZE = 20;

    public static void main(String[] args) {
        // Create a TreeMap to store Employee objects, using Employee name as the key.
        // TreeMap sorts the entries by keys.
        Map<String, Employee> employeeMap = new TreeMap<>();

        // Add employees to the TreeMap.
        System.out.println("Adding Employees...");
        addEmployees(employeeMap);

        // Display all employees in sorted order by name.
        System.out.println("\nAll Employees (Sorted by Name):");
        displayEmployees(employeeMap);

        // Search for an employee by name.
        String searchName = "Employee_3";
        System.out.println("\nSearching for Employee with name '" + searchName + "':");
        searchEmployee(employeeMap, searchName);

        // Remove an employee by name.
        String removeName = "Employee_3";
        System.out.println("\nRemoving Employee with name '" + removeName + "':");
        removeEmployee(employeeMap, removeName);

        // Attempt to remove a non-existing employee.
        String nonExistingName = "Employee_99";
        System.out.println("\nRemoving Non-Existing Employee with name '" + nonExistingName + "':");
        removeEmployee(employeeMap, nonExistingName);
    }

    /**
     * Adds Employee objects to the TreeMap.
     * Time Complexity: O(log n) per addition.
     * Space Complexity: O(n) - Space required to store n elements in the map.
     */
    private static void addEmployees(Map<String, Employee> employeeMap) {
        for (int i = 0; i < SIZE; i++) {
            Employee employee = Employee.builder(UUID.randomUUID()).name("Employee_" + i).build();
            employeeMap.put(employee.getName(), employee);
            System.out.println("Added: " + employee);
        }
    }

    /**
     * Displays all employees in the TreeMap.
     * Time Complexity: O(n) - Iterates through all elements in the map.
     * Space Complexity: O(1) - No additional data structures are used.
     */
    private static void displayEmployees(Map<String, Employee> employeeMap) {
        employeeMap.forEach((key, value) -> System.out.println(key + ": " + value));
    }

    /**
     * Searches for an employee by name in the TreeMap.
     * Time Complexity: O(log n) - TreeMap performs log(n) search due to its tree structure.
     * Space Complexity: O(1) - No additional data structures are used.
     */
    private static void searchEmployee(Map<String, Employee> employeeMap, String name) {
        Employee employee = employeeMap.get(name);
        if (employee != null) {
            System.out.println("Found: " + employee);
        } else {
            System.out.println("Employee with name '" + name + "' not found.");
        }
    }

    /**
     * Removes an employee from the TreeMap by name.
     * Time Complexity: O(log n) - TreeMap performs log(n) removal.
     * Space Complexity: O(1) - No additional data structures are used.
     */
    private static void removeEmployee(Map<String, Employee> employeeMap, String name) {
        Employee removedEmployee = employeeMap.remove(name);
        if (removedEmployee != null) {
            System.out.println("Removed: " + removedEmployee);
        } else {
            System.out.println("Employee with name '" + name + "' not found.");
        }
    }
}