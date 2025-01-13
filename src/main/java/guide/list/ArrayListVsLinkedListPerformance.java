package guide.list;

import guide.common.api.Employee;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class ArrayListVsLinkedListPerformance {

    public static void main(String[] args) {
        int size = 100000; // Set the size to 1 million for performance testing
        System.out.println("Performance Comparison: ArrayList vs LinkedList");

        // Pre-create the Employee objects
        List<Employee> employees = createEmployees(size);

        // 1. Add Operation Performance
        benchmarkAddOperation(employees);

        // 2. Access Operation Performance
        benchmarkAccessOperation(employees);

        // 3. Remove Operation Performance
        benchmarkRemoveOperation(employees);
    }

    // Pre-create Employee objects
    private static List<Employee> createEmployees(int size) {
        List<Employee> employees = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            employees.add(new Employee(UUID.randomUUID(), "Employee_" + i));
        }
        return employees;
    }

    // Benchmark Add Operation (using pre-created employees)
    private static void benchmarkAddOperation(List<Employee> employees) {
        List<Employee> arrayList = new ArrayList<>();
        List<Employee> linkedList = new LinkedList<>();

        System.out.println("\nAdd Operation:");

        // ArrayList Add
        long arrayListTime = measureTime(() -> {
            for (Employee employee : employees) {
                arrayList.add(employee);
            }
        });
        System.out.println("ArrayList (add): " + arrayListTime + " ms");

        // LinkedList Add (at tail, equivalent to addLast in LinkedList)
        long linkedListTime = measureTime(() -> {
            for (Employee employee : employees) {
                linkedList.add(employee);
            }
        });
        System.out.println("LinkedList (addLast): " + linkedListTime + " ms");

        // LinkedList Add (at head, equivalent to addFirst in LinkedList)
        long linkedListAddFirstTime = measureTime(() -> {
            for (Employee employee : employees) {
                linkedList.add(0, employee); // Simulating addFirst
            }
        });
        System.out.println("LinkedList (addFirst): " + linkedListAddFirstTime + " ms");

        // LinkedList Add (at specific index, equivalent to addAt in LinkedList)
        long linkedListAddAtTime = measureTime(() -> {
            for (int i = 0; i < employees.size(); i++) {
                linkedList.add(i, employees.get(i)); // Simulating addAt
            }
        });
        System.out.println("LinkedList (addAt): " + linkedListAddAtTime + " ms");
    }

    // Benchmark Access Operation
    private static void benchmarkAccessOperation(List<Employee> employees) {
        List<Employee> arrayList = new ArrayList<>(employees);
        List<Employee> linkedList = new LinkedList<>(employees);

        System.out.println("\nAccess Operation:");

        // ArrayList Access
        long arrayListTime = measureTime(() -> {
            for (int i = 0; i < employees.size(); i++) {
                arrayList.get(i); // Access Employee by index
            }
        });
        System.out.println("ArrayList (get): " + arrayListTime + " ms");

        // LinkedList Access
        long linkedListTime = measureTime(() -> {
            for (int i = 0; i < employees.size(); i++) {
                linkedList.get(i); // Access Employee by index
            }
        });
        System.out.println("LinkedList (get): " + linkedListTime + " ms");
    }

    // Benchmark Remove Operation
    private static void benchmarkRemoveOperation(List<Employee> employees) {
        List<Employee> arrayList = new ArrayList<>(employees);
        List<Employee> linkedList = new LinkedList<>(employees);

        System.out.println("\nRemove Operation:");

        // ArrayList Remove (by index)
        long arrayListTime = measureTime(() -> {
            for (int i = arrayList.size() - 1; i >= 0; i--) {
                arrayList.remove(i); // Remove Employee by index
            }
        });
        System.out.println("ArrayList (remove): " + arrayListTime + " ms");

        // LinkedList Remove (by index)
        long linkedListTime = measureTime(() -> {
            for (int i = linkedList.size() - 1; i >= 0; i--) {
                linkedList.remove(i); // Remove Employee by index
            }
        });
        System.out.println("LinkedList (remove): " + linkedListTime + " ms");

        // LinkedList Remove (first element, equivalent to removeFirst in LinkedList)
        long linkedListRemoveFirstTime = measureTime(() -> {
            while (!linkedList.isEmpty()) {
                linkedList.remove(0); // Simulating removeFirst
            }
        });
        System.out.println("LinkedList (removeFirst): " + linkedListRemoveFirstTime + " ms");

        // LinkedList Remove (last element, equivalent to removeLast in LinkedList)
        long linkedListRemoveLastTime = measureTime(() -> {
            while (!linkedList.isEmpty()) {
                linkedList.remove(linkedList.size() - 1); // Simulating removeLast
            }
        });
        System.out.println("LinkedList (removeLast): " + linkedListRemoveLastTime + " ms");

        // LinkedList Remove (from specific index, equivalent to removeAt in LinkedList)
        long linkedListRemoveAtTime = measureTime(() -> {
            for (int i = linkedList.size() - 1; i >= 0; i--) {
                linkedList.remove(i); // Simulating removeAt
            }
        });
        System.out.println("LinkedList (removeAt): " + linkedListRemoveAtTime + " ms");
    }

    // Helper method to measure execution time
    private static long measureTime(Runnable task) {
        long start = System.currentTimeMillis();
        task.run();
        return System.currentTimeMillis() - start;
    }
}