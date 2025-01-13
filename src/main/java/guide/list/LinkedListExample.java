package guide.list;

import guide.common.api.Employee;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class LinkedListExample {
    private final static int SIZE = 5;

    public static void main(String[] args) {
        // Create a LinkedList to store Employee objects.
        // No need for initial capacity, as LinkedList is dynamically sized and suitable for scenarios where the size is unbounded or unknown.
        List<Employee> employeeList = new LinkedList<>();

        // Add employees to the LinkedList (at the tail).
        System.out.println("Adding Employees...");
        addEmployees(employeeList);

        // Display all employees.
        System.out.println("\nAll Employees:");
        displayEmployees(employeeList);

        // Add an employee at the head.
        System.out.println("\nAdding an Employee at the head:");
        addEmployeeAtHead(employeeList);

        // Add an employee at somewhere in between.
        System.out.println("\nAdding an Employee at the specified index:");
        addEmployeeAt(employeeList, 2);

        // Add an employee at the tail.
        System.out.println("\nAdding an Employee at the tail:");
        addEmployeeAtTail(employeeList);


        // Remove an employee from the tail.
        System.out.println("\nRemoving Employee from the tail:");
        removeEmployeeFromTail(employeeList);

        // Remove an employee from the head.
        System.out.println("\nRemoving Employee from the specified index:");
        removeEmployeeAt(employeeList, 2);

        // Remove an employee from the head.
        System.out.println("\nRemoving Employee from the head:");
        removeEmployeeFromHead(employeeList);

        // Search for an employee in the list.
        System.out.println("\nSearching for an Employee:");
        searchEmployee(employeeList, "Employee 3");
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
     * Removes an employee from the list from the head.
     * Time Complexity: O(1) - Removing from the head of a LinkedList is O(1).
     * Space Complexity: O(1) - No additional data structures are used.
     */
    private static void removeEmployeeFromHead(List<Employee> employeeList) {
        if (!employeeList.isEmpty()) {
            Employee removedEmployee = employeeList.removeFirst();
            System.out.println("Removed from head: " + removedEmployee);
        } else {
            System.out.println("The list is empty.");
        }
    }

    /**
     * Removes an employee from the list from the head.
     * Time Complexity: O(n) - Removing from the specified index of a LinkedList is O(n).
     * Space Complexity: O(1) - No additional data structures are used.
     */
    private static void removeEmployeeAt(List<Employee> employeeList, int index) {
        if (!employeeList.isEmpty() && index >= 0 && index < employeeList.size()) {
            Employee removedEmployee = employeeList.remove(index);
            System.out.println("Removed from the specified index: " + removedEmployee);
        } else {
            System.out.println("The list is empty.");
        }
    }

    /**
     * Removes an employee from the list from the tail.
     * Time Complexity: O(1) - Removing from the tail of a LinkedList is O(1).
     * Space Complexity: O(1) - No additional data structures are used.
     */
    private static void removeEmployeeFromTail(List<Employee> employeeList) {
        if (!employeeList.isEmpty()) {
            Employee removedEmployee = employeeList.removeLast();
            System.out.println("Removed from tail: " + removedEmployee);
        } else {
            System.out.println("The list is empty.");
        }
    }

    /**
     * Adds an employee to the head of the list.
     * Time Complexity: O(1) - Inserting at the head of a LinkedList is O(1).
     * Space Complexity: O(1) - No additional data structures are used.
     */
    private static void addEmployeeAtHead(List<Employee> employeeList) {
        Employee employee = Employee.builder(UUID.randomUUID()).name("Head Employee").build();
        ((LinkedList<Employee>) employeeList).addFirst(employee);
        System.out.println("Added at head: " + employee);
    }

    /**
     * Adds an employee at a specified index in the LinkedList.
     * Time Complexity: O(n) - Inserting at a specific index requires traversal to the index, which is O(n).
     * Space Complexity: O(1) - No additional data structures are used.
     */
    private static void addEmployeeAt(List<Employee> employeeList, int index) {
        if (index >= 0 && index <= employeeList.size()) {  // Allow insertion at the end as well.
            Employee employee = Employee.builder(UUID.randomUUID()).name("Employee at index " + index).build();
            employeeList.add(index, employee);
            System.out.println("Added at index " + index + ": " + employee);
        } else {
            System.out.println("Invalid index.");
        }
    }

    /**
     * Adds an employee to the tail (end) of the list.
     * Time Complexity: O(1) - Inserting at the tail of a LinkedList is O(1).
     * Space Complexity: O(1) - No additional data structures are used.
     */
    private static void addEmployeeAtTail(List<Employee> employeeList) {
        Employee employee = Employee.builder(UUID.randomUUID()).name("Tail Employee").build();
        ((LinkedList<Employee>) employeeList).addLast(employee);
        System.out.println("Added at tail: " + employee);
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
     * Adds employees to the list (at the tail).
     * Time Complexity: O(1) per addition (amortized), O(n) total for n additions.
     * - Adding an element to the tail of a LinkedList is O(1).
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