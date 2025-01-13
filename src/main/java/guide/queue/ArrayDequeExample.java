package guide.queue;

import guide.common.api.Employee;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.UUID;

public class ArrayDequeExample {
    private final static int SIZE = 20;

    public static void main(String[] args) {

        // Create an ArrayDeque to store Employee objects.
        // Internally, ArrayDeque uses an array to store elements. The array will grow or shrink dynamically as elements are added or removed.
        //
        // Resizing Overhead:
        // - When the array reaches its capacity, it needs to be resized (usually doubling the size), which can be an expensive operation (O(n)).
        // - However, the doubling mechanism ensures that resizing happens only occasionally, amortized over multiple operations.
        //
        // Worst-Case Scenario:
        // - In some cases, where many elements are added and removed, frequent resizing of the array might cause performance degradation.
        // - The resizing operation can take O(n) time, which could be a bottleneck in high-throughput scenarios.
        //
        // ArrayDeque is efficient for FIFO (First-In-First-Out) operations when elements are added or removed from the front or back.
        //
        // Time Complexity:
        // - O(1) for adding/removing elements to/from the front or back of the deque on average (amortized time).
        // - O(n) worst-case time for resizing during add operations.
        //
        // Space Complexity: O(n) - Space required to store n elements in the deque.
        Deque<Employee> employeeQueue = new ArrayDeque<>(SIZE);

        // Add employees to the ArrayDeque (acting as a queue).
        System.out.println("Adding Employees...");
        addEmployees(employeeQueue);

        // Display all employees in the queue.
        System.out.println("\nAll Employees in Queue:");
        displayEmployees(employeeQueue);

        // Search for an employee in the queue (not the most efficient as this involves linear search).
        Employee dummyEmployee = Employee.builder(UUID.randomUUID()).name("Dummy").build();
        System.out.println("\nSearching for an Employee:" + dummyEmployee);
        employeeQueue.add(dummyEmployee);
        searchEmployee(employeeQueue, dummyEmployee);

        // Search for an employee by name.
        System.out.println("\nSearching for an Employee by Name:");
        searchEmployee(employeeQueue, "Employee_3");

        // Remove an employee from the front of the queue.
        dummyEmployee = Employee.builder(UUID.randomUUID()).name("Dummy").build();
        System.out.println("\nRemoving Employee from the front of the Queue:" + dummyEmployee);
        employeeQueue.add(dummyEmployee);
        removeEmployee(employeeQueue, dummyEmployee);

        // Attempt to remove a non-existing employee.
        System.out.println("\nRemoving Non-Existing Employee 'Employee_99' from the Queue:");
        removeEmployee(employeeQueue, "Employee_99");
    }

    /**
     * Adds Employee objects to the ArrayDeque (acting as a queue).
     * Time Complexity: O(1) - Average time complexity for adding elements to the back of the queue.
     * Space Complexity: O(n) - Space required to store n elements in the deque.
     */
    private static void addEmployees(Deque<Employee> employeeQueue) {
        for (int i = 0; i < SIZE; i++) {
            Employee employee = Employee.builder(UUID.randomUUID()).name("Employee_" + i).build();
            employeeQueue.add(employee);
            System.out.println("Added: " + employee);
        }
    }

    /**
     * Displays all employees in the ArrayDeque (queue).
     * Time Complexity: O(n) - Iterates through all elements in the queue.
     * Space Complexity: O(1) - No additional data structures are used.
     */
    private static void displayEmployees(Deque<Employee> employeeQueue) {
        employeeQueue.forEach(System.out::println);
    }

    /**
     * Searches for an Employee object in the ArrayDeque (queue).
     * - This method assumes you have access to the Employee object you want to search for.
     * - The search leverages the `equals()` method for comparison, so it may take O(n) time.
     * Time Complexity: O(n) - In the worst case, the search will require checking each element in the deque.
     * Space Complexity: O(1) - No additional data structures are used.
     */
    private static void searchEmployee(Deque<Employee> employeeQueue, Employee employee) {
        if (employeeQueue.contains(employee)) {
            System.out.println("Found Employee: " + employee);
        } else {
            System.out.println("Employee '" + employee + "' not found.");
        }
    }

    /**
     * Searches for an Employee by name in the ArrayDeque (queue).
     * - This method iterates through all elements in the deque to find a match by name.
     * - Searching by name is O(n) because ArrayDeque doesn't provide an indexed search.
     * Time Complexity: O(n) - Iterates through all elements to find a match by name.
     * Space Complexity: O(1) - No additional data structures are used.
     */
    private static void searchEmployee(Deque<Employee> employeeQueue, final String name) {
        for (Employee employee : employeeQueue) {
            if (employee.getName().equals(name)) {
                System.out.println("Found: " + employee);
                return;
            }
        }
        System.out.println("Employee with name '" + name + "' not found.");
    }

    /**
     * Removes an Employee object from the ArrayDeque (queue).
     * - This method removes the employee from the front of the queue (FIFO behavior).
     * Time Complexity: O(1) - Average time complexity for removal from the front of the queue.
     * Space Complexity: O(1) - No additional data structures are used.
     */
    private static void removeEmployee(Deque<Employee> employeeQueue, Employee employee) {
        boolean removed = employeeQueue.remove(employee);
        if (removed) {
            System.out.println("Removed Employee: " + employee);
        } else {
            System.out.println("Employee '" + employee + "' not found.");
        }
    }

    /**
     * Removes an Employee from the ArrayDeque (queue) by name.
     * - This method iterates through the deque to find the employee by name and remove it.
     * - It uses O(n) time because ArrayDeque doesn't support removal by name directly.
     * Time Complexity: O(n) - Iterates through the deque to find and remove the employee by name.
     * Space Complexity: O(1) - No additional data structures are used.
     */
    private static void removeEmployee(Deque<Employee> employeeQueue, String name) {
        boolean removed = employeeQueue.removeIf(employee -> employee.getName().equals(name));
        if (removed) {
            System.out.println("Removed Employee with name: " + name);
        } else {
            System.out.println("Employee with name '" + name + "' not found.");
        }
    }
}