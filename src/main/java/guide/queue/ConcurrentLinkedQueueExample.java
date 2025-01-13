package guide.queue;

import guide.common.api.Employee;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.UUID;

public class ConcurrentLinkedQueueExample {
    private final static int SIZE = 20;

    public static void main(String[] args) {

        // Create a ConcurrentLinkedQueue to store Employee objects.
        // Internally, ConcurrentLinkedQueue uses a non-blocking algorithm based on a lock-free linked node structure.
        //
        // Thread-Safety:
        // - This queue is designed to be thread-safe, allowing multiple threads to concurrently add and remove elements.
        // - It uses atomic operations like CAS (Compare-And-Swap) to ensure that operations are thread-safe without locking, which can be more efficient than traditional locking mechanisms.
        //
        // Performance Considerations:
        // - It provides good performance in multi-threaded environments, especially when there are frequent enqueuing and dequeuing operations.
        // - It avoids blocking and contention, so it is particularly useful for highly concurrent systems where blocking queues could be a bottleneck.
        // - However, operations can still be subject to contention and may experience some delays if multiple threads are competing for the same resources.
        //
        // Time Complexity:
        // - O(1) for adding and removing elements in a thread-safe manner.
        // - The underlying algorithm ensures that enqueuing and dequeuing remain efficient even under contention.
        //
        // Space Complexity: O(n) - Space required to store n elements in the queue.
        ConcurrentLinkedQueue<Employee> employeeQueue = new ConcurrentLinkedQueue<>();

        // Add employees to the ConcurrentLinkedQueue (acting as a queue).
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
     * Adds Employee objects to the ConcurrentLinkedQueue (acting as a queue).
     * Time Complexity: O(1) - Average time complexity for adding elements to the back of the queue.
     * Space Complexity: O(n) - Space required to store n elements in the queue.
     */
    private static void addEmployees(ConcurrentLinkedQueue<Employee> employeeQueue) {
        for (int i = 0; i < SIZE; i++) {
            Employee employee = Employee.builder(UUID.randomUUID()).name("Employee_" + i).build();
            employeeQueue.add(employee);
            System.out.println("Added: " + employee);
        }
    }

    /**
     * Displays all employees in the ConcurrentLinkedQueue (queue).
     * Time Complexity: O(n) - Iterates through all elements in the queue.
     * Space Complexity: O(1) - No additional data structures are used.
     */
    private static void displayEmployees(ConcurrentLinkedQueue<Employee> employeeQueue) {
        employeeQueue.forEach(System.out::println);
    }

    /**
     * Searches for an Employee object in the ConcurrentLinkedQueue (queue).
     * - This method assumes you have access to the Employee object you want to search for.
     * - The search leverages the `equals()` method for comparison, so it may take O(n) time.
     * Time Complexity: O(n) - In the worst case, the search will require checking each element in the queue.
     * Space Complexity: O(1) - No additional data structures are used.
     */
    private static void searchEmployee(ConcurrentLinkedQueue<Employee> employeeQueue, Employee employee) {
        if (employeeQueue.contains(employee)) {
            System.out.println("Found Employee: " + employee);
        } else {
            System.out.println("Employee '" + employee + "' not found.");
        }
    }

    /**
     * Searches for an Employee by name in the ConcurrentLinkedQueue (queue).
     * - This method iterates through all elements in the queue to find a match by name.
     * - Searching by name is O(n) because ConcurrentLinkedQueue doesn't provide indexed access.
     * Time Complexity: O(n) - Iterates through all elements to find a match by name.
     * Space Complexity: O(1) - No additional data structures are used.
     */
    private static void searchEmployee(ConcurrentLinkedQueue<Employee> employeeQueue, final String name) {
        for (Employee employee : employeeQueue) {
            if (employee.getName().equals(name)) {
                System.out.println("Found: " + employee);
                return;
            }
        }
        System.out.println("Employee with name '" + name + "' not found.");
    }

    /**
     * Removes an Employee object from the ConcurrentLinkedQueue (queue).
     * - This method removes the employee from the front of the queue (FIFO behavior).
     * Time Complexity: O(1) - Average time complexity for removal from the front of the queue.
     * Space Complexity: O(1) - No additional data structures are used.
     */
    private static void removeEmployee(ConcurrentLinkedQueue<Employee> employeeQueue, Employee employee) {
        boolean removed = employeeQueue.remove(employee);
        if (removed) {
            System.out.println("Removed Employee: " + employee);
        } else {
            System.out.println("Employee '" + employee + "' not found.");
        }
    }

    /**
     * Removes an Employee from the ConcurrentLinkedQueue (queue) by name.
     * - This method iterates through the queue to find the employee by name and remove it.
     * - It uses O(n) time because ConcurrentLinkedQueue doesn't support removal by name directly.
     * Time Complexity: O(n) - Iterates through the queue to find and remove the employee by name.
     * Space Complexity: O(1) - No additional data structures are used.
     */
    private static void removeEmployee(ConcurrentLinkedQueue<Employee> employeeQueue, String name) {
        boolean removed = employeeQueue.removeIf(employee -> employee.getName().equals(name));
        if (removed) {
            System.out.println("Removed Employee with name: " + name);
        } else {
            System.out.println("Employee with name '" + name + "' not found.");
        }
    }
}