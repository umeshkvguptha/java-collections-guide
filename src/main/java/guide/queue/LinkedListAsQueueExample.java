package guide.queue;

import guide.common.api.Employee;

import java.util.LinkedList;
import java.util.Queue;
import java.util.UUID;

public class LinkedListAsQueueExample {
    private final static int SIZE = 20;

    public static void main(String[] args) {

        // Create a LinkedList to act as a Queue to store Employee objects.
        // Internally, LinkedList implements the Queue interface and uses a doubly-linked list.
        // It allows adding, removing, and accessing elements in a FIFO (First-In-First-Out) order.

        Queue<Employee> employeeQueue = new LinkedList<>();

        // Add employees to the Queue.
        System.out.println("Adding Employees...");
        addEmployees(employeeQueue);

        // Display all employees in the queue.
        System.out.println("\nAll Employees:");
        displayEmployees(employeeQueue);

        // Peek at the head of the queue (first element).
        System.out.println("\nPeeking the first Employee:");
        peekEmployee(employeeQueue);

        // Remove an employee from the queue.
        Employee dummyEmployee = Employee.builder(UUID.randomUUID()).name("Dummy").build(); // Dummy object to add and remove.
        System.out.println("\nAdding and Removing Employee:" + dummyEmployee);
        employeeQueue.add(dummyEmployee);
        removeEmployee(employeeQueue, dummyEmployee);

        // Remove the first employee in the queue.
        System.out.println("\nRemoving the first Employee:");
        removeFirstEmployee(employeeQueue);

        // Attempt to remove a non-existing employee (by name).
        System.out.println("\nRemoving Non-Existing Employee 'Employee_99':");
        removeEmployeeByName(employeeQueue, "Employee_99");
    }

    /**
     * Adds Employee objects to the Queue (LinkedList).
     * Time Complexity: O(1) per addition, as LinkedList maintains a reference to both the head and tail.
     * Space Complexity: O(n) - Space required to store n elements in the queue.
     */
    private static void addEmployees(Queue<Employee> employeeQueue) {
        for (int i = 0; i < SIZE; i++) {
            Employee employee = Employee.builder(UUID.randomUUID()).name("Employee_" + i).build();
            employeeQueue.add(employee);
            System.out.println("Added: " + employee);
        }
    }

    /**
     * Displays all employees in the Queue.
     * Time Complexity: O(n) - Iterates through all elements in the queue.
     * Space Complexity: O(1) - No additional data structures are used.
     */
    private static void displayEmployees(Queue<Employee> employeeQueue) {
        employeeQueue.forEach(System.out::println);
    }

    /**
     * Peeks the first employee in the queue.
     * - Peeking does not remove the element from the queue.
     * Time Complexity: O(1) - Directly accesses the head of the queue.
     * Space Complexity: O(1) - No additional data structures are used.
     */
    private static void peekEmployee(Queue<Employee> employeeQueue) {
        Employee firstEmployee = employeeQueue.peek();
        if (firstEmployee != null) {
            System.out.println("First Employee in Queue: " + firstEmployee);
        } else {
            System.out.println("The queue is empty.");
        }
    }

    /**
     * Removes the first employee from the Queue.
     * - Removes the element at the head of the queue.
     * Time Complexity: O(1) - Directly removes the head of the queue.
     * Space Complexity: O(1) - No additional data structures are used.
     */
    private static void removeFirstEmployee(Queue<Employee> employeeQueue) {
        Employee removedEmployee = employeeQueue.poll();
        if (removedEmployee != null) {
            System.out.println("Removed Employee: " + removedEmployee);
        } else {
            System.out.println("The queue is empty.");
        }
    }

    /**
     * Removes an Employee object from the Queue.
     * - This method removes the specific Employee object from the queue.
     * Time Complexity: O(n) - May require scanning through the queue to find the element.
     * Space Complexity: O(1) - No additional data structures are used.
     */
    private static void removeEmployee(Queue<Employee> employeeQueue, Employee employee) {
        boolean removed = employeeQueue.remove(employee);
        if (removed) {
            System.out.println("Removed Employee: " + employee);
        } else {
            System.out.println("Employee '" + employee + "' not found in the queue.");
        }
    }

    /**
     * Removes an Employee from the Queue by name.
     * - This method iterates through all elements in the queue to find and remove the employee by name.
     * Time Complexity: O(n) - Linear time complexity as we may need to scan through the entire queue.
     * Space Complexity: O(1) - No additional data structures are used.
     */
    private static void removeEmployeeByName(Queue<Employee> employeeQueue, String name) {
        boolean removed = employeeQueue.removeIf(employee -> employee.getName().equals(name));
        if (removed) {
            System.out.println("Removed Employee with name: " + name);
        } else {
            System.out.println("Employee with name '" + name + "' not found.");
        }
    }
}