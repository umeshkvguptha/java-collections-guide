package guide.map;

import guide.common.api.Employee;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class LinkedHashMapExample {
    private final static int SIZE = 20;

    public static void main(String[] args) {
        // Create a LinkedHashMap to store Employee objects, using Employee name as the key.
        // LinkedHashMap maintains insertion order of elements.
        Map<String, Employee> employeeMap = new LinkedHashMap<>(SIZE);

        // Add employees to the LinkedHashMap.
        System.out.println("Adding Employees...");
        addEmployees(employeeMap);

        // Display all employees in insertion order.
        System.out.println("\nAll Employees:");
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
     * Adds Employee objects to the LinkedHashMap.
     * Time Complexity: O(1) per addition on average.
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
     * Displays all employees in the LinkedHashMap.
     * Time Complexity: O(n) - Iterates through all elements in the map.
     * Space Complexity: O(1) - No additional data structures are used.
     */
    private static void displayEmployees(Map<String, Employee> employeeMap) {
        employeeMap.forEach((key, value) -> System.out.println(key + ": " + value));
    }

    /**
     * Searches for an employee by name in the LinkedHashMap.
     * Time Complexity: O(1) - LinkedHashMap provides average constant time lookup.
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
     * Removes an employee from the LinkedHashMap by name.
     * Time Complexity: O(1) - LinkedHashMap provides average constant time removal.
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