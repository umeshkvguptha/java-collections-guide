package guide.set;

import guide.common.api.Employee;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

public class LinkedHashSetExample {
    private final static int SIZE = 20;

    public static void main(String[] args) {

        // Create a LinkedHashSet to store Employee objects.
        // Internally, LinkedHashSet is backed by a LinkedHashMap where:
        // - The keys are the elements in the set.
        // - The values are a constant placeholder object (usually `PRESENT`).
        // - A doubly-linked list connects the elements in the order they were inserted.
        //
        // Null Handling:
        // - LinkedHashSet allows a single `null` element, just like HashSet.
        //
        // Iteration Order:
        // - LinkedHashSet maintains insertion order, unlike HashSet, which does not guarantee any order.
        //
        Set<Employee> employeeSet = new LinkedHashSet<>(SIZE);

        // Add employees to the LinkedHashSet.
        System.out.println("Adding Employees...");
        addEmployees(employeeSet);

        // Display all employees.
        System.out.println("\nAll Employees:");
        displayEmployees(employeeSet);

        // Search for an employee in the set.
        Employee dummyEmployee = Employee.builder(UUID.randomUUID()).name("Dummy").build(); // Dummy object with the same name.
        System.out.println("\nSearching for an Employee:" + dummyEmployee);
        employeeSet.add(dummyEmployee);
        searchEmployee(employeeSet, dummyEmployee);

        // Search for an employee by name.
        System.out.println("\nSearching for an Employee:");
        searchEmployee(employeeSet, "Employee_3");

        // Remove an employee.
        dummyEmployee = Employee.builder(UUID.randomUUID()).name("Dummy").build(); // Dummy object with the same name.
        System.out.println("\nRemoving Employee:" + dummyEmployee);
        employeeSet.add(dummyEmployee);
        removeEmployee(employeeSet, dummyEmployee);

        // Attempt to remove a non-existing employee.
        System.out.println("\nRemoving Non-Existing Employee 'Employee_99':");
        removeEmployee(employeeSet, "Employee_99");
    }

    /**
     * Adds Employee objects to the LinkedHashSet.
     * Internally:
     * - The LinkedHashSet computes the hash code of each element and maps it to a bucket, like HashSet.
     * - It maintains a doubly-linked list connecting all elements in the order they were inserted.
     *   This ensures consistent iteration order.
     *
     * Handling Collisions:
     * - When two elements have the same hash code, they are stored in the same bucket.
     * - Since Java 8, such buckets use a TreeMap-like structure for elements, providing O(log n) performance for lookups and removals.
     *
     * Null Handling:
     * - LinkedHashSet allows a single `null` element. Additional `null` values are ignored.
     *
     * Time Complexity: O(1) per addition on average, O(n) total for n additions (amortized, accounting for rehashing).
     * Space Complexity: O(n) - Space required to store n elements in the set.
     */
    private static void addEmployees(Set<Employee> employeeSet) {
        for (int i = 0; i < SIZE; i++) {
            Employee employee = Employee.builder(UUID.randomUUID()).name("Employee_" + i).build();
            employeeSet.add(employee);
            System.out.println("Added: " + employee);
        }
    }

    /**
     * Displays all employees in the LinkedHashSet.
     * Time Complexity: O(n) - Iterates through all elements in the set.
     * Space Complexity: O(1) - No additional data structures are used.
     */
    private static void displayEmployees(Set<Employee> employeeSet) {
        employeeSet.forEach(System.out::println);
    }

    /**
     * Searches for an Employee object in the LinkedHashSet.
     * - This method assumes you have access to the Employee object you want to search for.
     * - The search leverages the hash code of the Employee object for efficient lookups.
     *
     * Time Complexity: O(1) - Average time complexity for lookups in a LinkedHashSet.
     * Space Complexity: O(1) - No additional data structures are used.
     */
    private static void searchEmployee(Set<Employee> employeeSet, Employee employee) {
        if (employeeSet.contains(employee)) {
            System.out.println("Found Employee: " + employee);
        } else {
            System.out.println("Employee '" + employee + "' not found.");
        }
    }

    /**
     * Searches for an Employee by name in the LinkedHashSet.
     * - This method iterates through all elements in the set to find a match by name.
     * - Even though LinkedHashSet provides O(1) lookups by object, searching by a specific attribute (name)
     *   requires linear traversal.
     *
     * Time Complexity: O(n) - Iterates through all elements to find a match by name.
     * Space Complexity: O(1) - No additional data structures are used.
     */
    private static void searchEmployee(Set<Employee> employeeSet, final String name) {
        for (Employee employee : employeeSet) {
            if (employee.getName().equals(name)) {
                System.out.println("Found: " + employee);
                return;
            }
        }
        System.out.println("Employee with name '" + name + "' not found.");
    }

    /**
     * Removes an Employee object from the LinkedHashSet.
     * - This method assumes you have access to the Employee object you want to remove.
     * - The removal leverages the hash code of the Employee object for efficient deletion.
     *
     * Time Complexity: O(1) - Average time complexity for removal in a LinkedHashSet.
     * Space Complexity: O(1) - No additional data structures are used.
     */
    private static void removeEmployee(Set<Employee> employeeSet, Employee employee) {
        boolean removed = employeeSet.remove(employee);
        if (removed) {
            System.out.println("Removed Employee: " + employee);
        } else {
            System.out.println("Employee '" + employee + "' not found.");
        }
    }

    /**
     * Removes an Employee from the LinkedHashSet by name.
     * - This method iterates through all elements in the set to find a match by name and remove it.
     * - This does not directly leverage the O(1) removal mechanism based on hash codes.
     *
     * Time Complexity: O(n) - Iterates through all elements to find and remove by name.
     * Space Complexity: O(1) - No additional data structures are used.
     */
    private static void removeEmployee(Set<Employee> employeeSet, String name) {
        boolean removed = employeeSet.removeIf(employee -> employee.getName().equals(name));
        if (removed) {
            System.out.println("Removed Employee with name: " + name);
        } else {
            System.out.println("Employee with name '" + name + "' not found.");
        }
    }
}