package guide.set;

import guide.common.api.Employee;

import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

public class TreeSetExample {
    private final static int SIZE = 20;

    public static void main(String[] args) {

        // Create a TreeSet to store Employee objects.
        // Internally, TreeSet is backed by a `TreeMap`, which implements a balanced binary search tree (Red-Black Tree).
        //
        // Ordering:
        // - TreeSet maintains elements in their natural order (based on Comparable) or a custom order (via Comparator).
        //
        // Null Handling:
        // - TreeSet does NOT allow `null` elements because it uses comparisons for ordering.
        //
        // Performance:
        // - Additions, removals, and lookups have a time complexity of O(log n) due to the tree structure.
        //
        // Requirements:
        // - Elements must be Comparable or a custom Comparator must be provided.
        //
        Set<Employee> employeeSet = new TreeSet<>((e1, e2) -> e1.getName().compareTo(e2.getName()));

        // Add employees to the TreeSet.
        System.out.println("Adding Employees...");
        addEmployees(employeeSet);

        // Display all employees.
        System.out.println("\nAll Employees:");
        displayEmployees(employeeSet);

        // Search for an employee in the set.
        Employee dummyEmployee = Employee.builder(UUID.randomUUID()).name("Dummy").build(); // Dummy object with the same name.
        System.out.println("\nSearching for an Employee: " + dummyEmployee);
        employeeSet.add(dummyEmployee);
        searchEmployee(employeeSet, dummyEmployee);

        // Search for an employee by name.
        System.out.println("\nSearching for an Employee:");
        searchEmployee(employeeSet, "Employee_3");

        // Remove an employee.
        dummyEmployee = Employee.builder(UUID.randomUUID()).name("Dummy").build(); // Dummy object with the same name.
        System.out.println("\nRemoving Employee: " + dummyEmployee);
        employeeSet.add(dummyEmployee);
        removeEmployee(employeeSet, dummyEmployee);

        // Attempt to remove a non-existing employee.
        System.out.println("\nRemoving Non-Existing Employee 'Employee_99':");
        removeEmployee(employeeSet, "Employee_99");
    }

    /**
     * Adds Employee objects to the TreeSet.
     * Internally:
     * - The TreeSet uses a Red-Black Tree to store elements in sorted order.
     * - Each element is compared using the Comparator provided or their natural ordering.
     *
     * Null Handling:
     * - TreeSet does NOT allow `null` elements because comparisons involving `null` throw NullPointerException.
     *
     * Time Complexity: O(log n) per addition due to tree balancing.
     * Space Complexity: O(n) - Space required to store n elements in the tree.
     */
    private static void addEmployees(Set<Employee> employeeSet) {
        for (int i = 0; i < SIZE; i++) {
            Employee employee = Employee.builder(UUID.randomUUID()).name("Employee_" + i).build();
            employeeSet.add(employee);
            System.out.println("Added: " + employee);
        }
    }

    /**
     * Displays all employees in the TreeSet.
     * - The employees are displayed in sorted order (based on their names in this example).
     *
     * Time Complexity: O(n) - Iterates through all elements in the set.
     * Space Complexity: O(1) - No additional data structures are used.
     */
    private static void displayEmployees(Set<Employee> employeeSet) {
        employeeSet.forEach(System.out::println);
    }

    /**
     * Searches for an Employee object in the TreeSet.
     * - This method assumes you have access to the Employee object you want to search for.
     * - The search leverages the tree structure for efficient lookups.
     *
     * Time Complexity: O(log n) - Average time complexity for lookups in a TreeSet.
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
     * Searches for an Employee by name in the TreeSet.
     * - This method iterates through all elements in the set to find a match by name.
     * - While TreeSet provides O(log n) lookups by object, searching by a specific attribute (name)
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
     * Removes an Employee object from the TreeSet.
     * - This method assumes you have access to the Employee object you want to remove.
     * - The removal leverages the tree structure for efficient deletion.
     *
     * Time Complexity: O(log n) - Average time complexity for removal in a TreeSet.
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
     * Removes an Employee from the TreeSet by name.
     * - This method iterates through all elements in the set to find a match by name and remove it.
     * - This does not directly leverage the O(log n) removal mechanism based on the tree structure.
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