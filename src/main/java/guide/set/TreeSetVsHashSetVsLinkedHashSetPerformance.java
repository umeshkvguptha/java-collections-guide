package guide.set;

import guide.common.api.Employee;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

public class TreeSetVsHashSetVsLinkedHashSetPerformance {
    private final static int SIZE = 1000_000; // Set size for benchmarking

    public static void main(String[] args) {
        System.out.println("Performance Comparison: TreeSet vs HashSet vs LinkedHashSet");

        // Pre-create the Employee objects
        Set<Employee> employees = createEmployees();

        // 1. Add Operation Performance
        benchmarkAddOperation(employees);

        // 2. Search Operation Performance
        benchmarkSearchOperation(employees);

        // 3. Remove Operation Performance
        benchmarkRemoveOperation(employees);
    }

    /**
     * Pre-creates Employee objects for benchmarking.
     */
    private static Set<Employee> createEmployees() {
        Set<Employee> employees = new HashSet<>(SIZE);
        for (int i = 0; i < SIZE; i++) {
            employees.add(new Employee(UUID.randomUUID(), "Employee_" + i));
        }
        return employees;
    }

    /**
     * Benchmark Add Operation.
     */
    private static void benchmarkAddOperation(Set<Employee> employees) {
        Set<Employee> hashSet = new HashSet<>(SIZE);
        Set<Employee> linkedHashSet = new LinkedHashSet<>(SIZE);
        Set<Employee> treeSet = new TreeSet<>((e1, e2) -> e1.getName().compareTo(e2.getName()));

        System.out.println("\nAdd Operation:");

        // HashSet Add
        long hashSetTime = measureTime(() -> {
            for (Employee employee : employees) {
                hashSet.add(employee);
            }
        });
        System.out.println("HashSet (add): " + hashSetTime + " ms");

        // LinkedHashSet Add
        long linkedHashSetTime = measureTime(() -> {
            for (Employee employee : employees) {
                linkedHashSet.add(employee);
            }
        });
        System.out.println("LinkedHashSet (add): " + linkedHashSetTime + " ms");

        // TreeSet Add
        long treeSetTime = measureTime(() -> {
            for (Employee employee : employees) {
                treeSet.add(employee);
            }
        });
        System.out.println("TreeSet (add): " + treeSetTime + " ms");
    }

    /**
     * Benchmark Search Operation.
     */
    private static void benchmarkSearchOperation(Set<Employee> employees) {
        Set<Employee> hashSet = new HashSet<>(employees);
        Set<Employee> linkedHashSet = new LinkedHashSet<>(employees);
        Set<Employee> treeSet = new TreeSet<>((e1, e2) -> e1.getName().compareTo(e2.getName()));
        treeSet.addAll(employees);

        Employee searchEmployee = employees.iterator().next(); // Pick the first employee for search

        System.out.println("\nSearch Operation:");

        // HashSet Search
        long hashSetTime = measureTime(() -> hashSet.contains(searchEmployee));
        System.out.println("HashSet (contains): " + hashSetTime + " ms");

        // LinkedHashSet Search
        long linkedHashSetTime = measureTime(() -> linkedHashSet.contains(searchEmployee));
        System.out.println("LinkedHashSet (contains): " + linkedHashSetTime + " ms");

        // TreeSet Search
        long treeSetTime = measureTime(() -> treeSet.contains(searchEmployee));
        System.out.println("TreeSet (contains): " + treeSetTime + " ms");
    }

    /**
     * Benchmark Remove Operation.
     */
    private static void benchmarkRemoveOperation(Set<Employee> employees) {
        Set<Employee> hashSet = new HashSet<>(employees);
        Set<Employee> linkedHashSet = new LinkedHashSet<>(employees);
        Set<Employee> treeSet = new TreeSet<>((e1, e2) -> e1.getName().compareTo(e2.getName()));
        treeSet.addAll(employees);

        System.out.println("\nRemove Operation:");

        // HashSet Remove
        long hashSetTime = measureTime(() -> {
            for (Employee employee : employees) {
                hashSet.remove(employee);
            }
        });
        System.out.println("HashSet (remove): " + hashSetTime + " ms");

        // LinkedHashSet Remove
        long linkedHashSetTime = measureTime(() -> {
            for (Employee employee : employees) {
                linkedHashSet.remove(employee);
            }
        });
        System.out.println("LinkedHashSet (remove): " + linkedHashSetTime + " ms");

        // TreeSet Remove
        long treeSetTime = measureTime(() -> {
            for (Employee employee : employees) {
                treeSet.remove(employee);
            }
        });
        System.out.println("TreeSet (remove): " + treeSetTime + " ms");
    }

    /**
     * Helper method to measure execution time.
     */
    private static long measureTime(Runnable task) {
        long start = System.currentTimeMillis();
        task.run();
        return System.currentTimeMillis() - start;
    }
}