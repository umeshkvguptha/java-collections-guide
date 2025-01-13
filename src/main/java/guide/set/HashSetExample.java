package guide.set;

import guide.common.api.Employee;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class HashSetExample {
    private final static int SIZE = 20;

    public static void main(String[] args) {

        // Create a HashSet to store Employee objects.
        // Internally, HashSet is backed by a HashMap where:
        // - The keys are the elements in the HashSet.
        // - The value for each key is a constant placeholder object (usually `PRESENT`).
        //
        // Null Handling:
        // - HashSet allows a single `null` element because HashMap keys can have one null.
        // - Additional null values are ignored because sets do not allow duplicates.
        //
        // Size Specification:
        // - The specified initial size helps optimize performance by reducing the need for resizing and rehashing
        //   as the HashSet grows. If the size is not specified, the default initial capacity is 16, with a load factor of 0.75.
        // - When the load factor threshold is exceeded (i.e., `size > capacity * loadFactor`), the HashSet resizes
        //   and rehashes its contents into a larger internal structure, which is costly in terms of performance.
        //
        Set<Employee> employeeSet = new HashSet<>(SIZE);

        // Add employees to the HashSet.
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

        // Search for an employee in the set.
        System.out.println("\nSearching for an Employee:");
        searchEmployee(employeeSet, "Employee_3");

        // Remove an employee.
        dummyEmployee = Employee.builder(UUID.randomUUID()).name("Dummy").build(); // Dummy object with the same name.
        System.out.println("\nRemoving Employee':" + dummyEmployee);
        employeeSet.add(dummyEmployee);
        removeEmployee(employeeSet, dummyEmployee);

        // Attempt to remove a non-existing employee.
        System.out.println("\nRemoving Non-Existing Employee 'Employee_99':");
        removeEmployee(employeeSet, "Employee_99");
    }

    /**
     * O(1) Search for an employee by name in the HashSet.
     * This method assumes you have access to the Employee object you want to search for.
     * Time Complexity: O(1) - Leverages the HashSet's contains method, which uses hash codes.
     * Space Complexity: O(1) - No additional data structures are used.
     */
    private static void searchEmployee(Set<Employee> employeeSet, Employee employee) {
        if (employeeSet.contains(employee)) {
            System.out.println("Found Employee : " + employee);
        } else {
            System.out.println("Employee '" + employee + "' not found.");
        }
    }

    /**
     * Searches for an employee by name in the HashSet.
     * Time Complexity: O(n) - Even though HashSet provides average O(1) lookups, this method uses
     * an iteration through all elements to match by name, which results in O(n).
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
     * O(1) Removal of an employee from the HashSet.
     * This method assumes you have access to the Employee object you want to remove.
     * Time Complexity: O(1) - HashSet provides average constant time removal based on hash codes.
     * Space Complexity: O(1) - No additional data structures are used.
     */
    private static void removeEmployee(Set<Employee> employeeSet, Employee employee) {
        boolean removed = employeeSet.remove(employee);
        if (removed) {
            System.out.println("Removed Employee : " + employee);
        } else {
            System.out.println("Employee '" + employee + "' not found.");
        }
    }

    /**
     * Removes an employee from the HashSet by name.
     * Time Complexity: O(n) - removeIf iterates through all elements in the set to match and remove elements.
     * - This does not directly leverage the HashSet's internal O(1) removal mechanism based on hash codes.
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

    /**
     * Displays all employees in the HashSet.
     * Time Complexity: O(n) - Iterates through all elements in the set.
     * Space Complexity: O(1) - No additional data structures are used.
     */
    private static void displayEmployees(Set<Employee> employeeSet) {
        employeeSet.forEach(System.out::println);
    }

    /**
     * Adds Employee objects to the HashSet.
     * Internally:
     * - When adding an element, the HashSet computes the hash code of the element and maps it to a bucket.
     * - If there are hash collisions (two elements with the same hash), they are stored in the same bucket.
     * - Starting with Java 8, elements in the same bucket are stored in a TreeMap-like structure (instead of a linked list),
     *   providing O(log n) performance for lookups and removals in the event of collisions.
     *
     * Null Handling:
     * - HashSet allows a single `null` value. Adding another `null` will have no effect because sets disallow duplicates.
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

        // Add a null value to demonstrate null handling in HashSet.
        // However, Employee::hashCode() optimized to use only UUID, so we cannot have null
        // Employee nullEmployee = null;
        // employeeSet.add(nullEmployee);
        // System.out.println("Added null Employee.");
    }
}