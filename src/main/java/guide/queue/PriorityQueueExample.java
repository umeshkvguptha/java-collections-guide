package guide.queue;

import guide.common.api.Employee;

import java.util.PriorityQueue;
import java.util.UUID;

public class PriorityQueueExample {
    private final static int SIZE = 20;

    public static void main(String[] args) {

        // Create a PriorityQueue to store Employee objects.
        // Internally, PriorityQueue uses a heap data structure (usually a binary heap).
        //
        // Priority Queue:
        // - Elements are dequeued in order of priority. The default behavior is to use the natural ordering of the elements.
        // - If you use custom objects, you must implement the `Comparable` interface or provide a `Comparator`.
        //
        // Time Complexity:
        // - Insertion (enqueue): O(log n) - Inserting an element requires reordering the heap.
        // - Removal (dequeue): O(log n) - Removing the top element (the one with the highest priority) requires reordering the heap.
        //
        // Space Complexity: O(n) - Space required to store n elements in the queue.
        PriorityQueue<Employee> employeeQueue = new PriorityQueue<>(SIZE, (Employee e1, Employee e2) -> e1.getName().compareTo(e2.getName()));

        // Add employees to the PriorityQueue.
        System.out.println("Adding Employees...");
        addEmployees(employeeQueue);

        // Display the top element in the queue (highest priority).
        System.out.println("\nTop Employee in Queue (Highest Priority):");
        displayTopEmployee(employeeQueue);

        // Remove an employee from the queue (highest priority).
        System.out.println("\nRemoving Employee from the queue (Highest Priority):");
        removeEmployee(employeeQueue);

        // Display all employees after removal.
        System.out.println("\nAll Employees After Removal:");
        displayEmployees(employeeQueue);

        // Search for an employee in the queue.
        Employee dummyEmployee = Employee.builder(UUID.randomUUID()).name("Dummy").build();
        System.out.println("\nSearching for an Employee:" + dummyEmployee);
        employeeQueue.add(dummyEmployee);
        searchEmployee(employeeQueue, dummyEmployee);

        // Attempt to remove a non-existing employee.
        System.out.println("\nRemoving Non-Existing Employee 'Employee_99' from the Queue:");
        removeEmployee(employeeQueue, "Employee_99");
    }

    /**
     * Adds Employee objects to the PriorityQueue.
     * - The PriorityQueue will reorder the elements based on their natural ordering or the provided comparator.
     * Time Complexity: O(log n) - Each insertion involves a reordering of the heap.
     * Space Complexity: O(n) - Space required to store n elements in the queue.
     */
    private static void addEmployees(PriorityQueue<Employee> employeeQueue) {
        for (int i = 0; i < SIZE; i++) {
            Employee employee = Employee.builder(UUID.randomUUID()).name("Employee_" + i).build();
            employeeQueue.add(employee);
            System.out.println("Added: " + employee);
        }
    }

    /**
     * Displays the employee with the highest priority in the PriorityQueue.
     * - The top element in a PriorityQueue is always the one with the highest priority.
     * Time Complexity: O(1) - Accessing the top element does not require reordering.
     * Space Complexity: O(1) - No additional data structures are used.
     */
    private static void displayTopEmployee(PriorityQueue<Employee> employeeQueue) {
        Employee topEmployee = employeeQueue.peek(); // Retrieves but does not remove the top element.
        if (topEmployee != null) {
            System.out.println("Top Employee: " + topEmployee);
        } else {
            System.out.println("The queue is empty.");
        }
    }

    /**
     * Removes an Employee from the PriorityQueue (the highest priority element).
     * Time Complexity: O(log n) - Removing the top element requires reordering the heap.
     * Space Complexity: O(1) - No additional data structures are used.
     */
    private static void removeEmployee(PriorityQueue<Employee> employeeQueue) {
        Employee removedEmployee = employeeQueue.poll(); // Retrieves and removes the top element.
        if (removedEmployee != null) {
            System.out.println("Removed Employee: " + removedEmployee);
        } else {
            System.out.println("The queue is empty.");
        }
    }

    /**
     * Removes an Employee from the PriorityQueue by name.
     * - This method iterates through the queue to find and remove the employee by name.
     * - Since PriorityQueue is not designed for indexed access, this is an O(n) operation.
     * Time Complexity: O(n) - Iterates through the entire queue to find the element by name.
     * Space Complexity: O(1) - No additional data structures are used.
     */
    private static void removeEmployee(PriorityQueue<Employee> employeeQueue, String name) {
        boolean removed = employeeQueue.removeIf(employee -> employee.getName().equals(name));
        if (removed) {
            System.out.println("Removed Employee with name: " + name);
        } else {
            System.out.println("Employee with name '" + name + "' not found.");
        }
    }

    /**
     * Displays all employees in the PriorityQueue.
     * - The PriorityQueue doesn't provide an easy way to iterate in order, but you can still iterate using a basic forEach.
     * Time Complexity: O(n) - Iterates through all elements in the queue.
     * Space Complexity: O(1) - No additional data structures are used.
     */
    private static void displayEmployees(PriorityQueue<Employee> employeeQueue) {
        employeeQueue.forEach(System.out::println);
    }

    /**
     * Searches for an Employee object in the PriorityQueue.
     * - This method assumes you have access to the Employee object you want to search for.
     * - The search leverages the `equals()` method for comparison, so it may take O(n) time.
     * Time Complexity: O(n) - In the worst case, the search will require checking each element in the queue.
     * Space Complexity: O(1) - No additional data structures are used.
     */
    private static void searchEmployee(PriorityQueue<Employee> employeeQueue, Employee employee) {
        if (employeeQueue.contains(employee)) {
            System.out.println("Found Employee: " + employee);
        } else {
            System.out.println("Employee '" + employee + "' not found.");
        }
    }
}