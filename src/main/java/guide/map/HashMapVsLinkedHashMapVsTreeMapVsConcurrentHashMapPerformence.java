package guide.map;

import guide.common.api.Employee;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.UUID;

public class HashMapVsLinkedHashMapVsTreeMapVsConcurrentHashMapPerformence {
    private final static int SIZE = 1000000; // Map size for benchmarking

    public static void main(String[] args) {
        System.out.println("Performance Comparison: HashMap vs LinkedHashMap vs TreeMap vs ConcurrentHashMap");

        // Pre-create the Employee objects
        Map<String, Employee> employees = createEmployees();

        // 1. Add Operation Performance
        benchmarkAddOperation(employees);

        // 2. Search Operation Performance
        benchmarkSearchOperation(employees);

        // 3. Remove Operation Performance
        benchmarkRemoveOperation(employees);

        // 4. Iteration Operation
        benchmarkIterationOperation(employees);
    }

    /**
     * Pre-creates Employee objects for benchmarking.
     */
    private static Map<String, Employee> createEmployees() {
        Map<String, Employee> employees = new HashMap<>(SIZE);
        for (int i = 0; i < SIZE; i++) {
            Employee employee = new Employee(UUID.randomUUID(), "Employee_" + i);
            employees.put(employee.getName(), employee);
        }
        return employees;
    }

    /**
     * Benchmark Add Operation (put).
     */
    private static void benchmarkAddOperation(Map<String, Employee> employees) {
        Map<String, Employee> hashMap = new HashMap<>(SIZE);
        Map<String, Employee> linkedHashMap = new LinkedHashMap<>(SIZE);
        Map<String, Employee> treeMap = new TreeMap<>();
        Map<String, Employee> concurrentHashMap = new ConcurrentHashMap<>(SIZE);

        System.out.println("\nAdd Operation:");

        // HashMap Add
        long hashMapTime = measureTime(() -> {
            for (Employee employee : employees.values()) {
                hashMap.put(employee.getName(), employee);
            }
        });
        System.out.println("HashMap (put): " + hashMapTime + " ms");

        // LinkedHashMap Add
        long linkedHashMapTime = measureTime(() -> {
            for (Employee employee : employees.values()) {
                linkedHashMap.put(employee.getName(), employee);
            }
        });
        System.out.println("LinkedHashMap (put): " + linkedHashMapTime + " ms");

        // TreeMap Add
        long treeMapTime = measureTime(() -> {
            for (Employee employee : employees.values()) {
                treeMap.put(employee.getName(), employee);
            }
        });
        System.out.println("TreeMap (put): " + treeMapTime + " ms");

        // ConcurrentHashMap Add
        long concurrentHashMapTime = measureTime(() -> {
            for (Employee employee : employees.values()) {
                concurrentHashMap.put(employee.getName(), employee);
            }
        });
        System.out.println("ConcurrentHashMap (put): " + concurrentHashMapTime + " ms");
    }

    /**
     * Benchmark Search Operation (get).
     */
    private static void benchmarkSearchOperation(Map<String, Employee> employees) {
        Map<String, Employee> hashMap = new HashMap<>(employees);
        Map<String, Employee> linkedHashMap = new LinkedHashMap<>(employees);
        Map<String, Employee> treeMap = new TreeMap<>(employees);
        Map<String, Employee> concurrentHashMap = new ConcurrentHashMap<>(employees);

        Employee searchEmployee = employees.values().iterator().next(); // Pick the first employee for search

        System.out.println("\nSearch Operation:");

        // HashMap Search
        long hashMapTime = measureTime(() -> hashMap.get(searchEmployee.getName()));
        System.out.println("HashMap (get): " + hashMapTime + " ms");

        // LinkedHashMap Search
        long linkedHashMapTime = measureTime(() -> linkedHashMap.get(searchEmployee.getName()));
        System.out.println("LinkedHashMap (get): " + linkedHashMapTime + " ms");

        // TreeMap Search
        long treeMapTime = measureTime(() -> treeMap.get(searchEmployee.getName()));
        System.out.println("TreeMap (get): " + treeMapTime + " ms");

        // ConcurrentHashMap Search
        long concurrentHashMapTime = measureTime(() -> concurrentHashMap.get(searchEmployee.getName()));
        System.out.println("ConcurrentHashMap (get): " + concurrentHashMapTime + " ms");
    }

    /**
     * Benchmark Remove Operation (remove).
     */
    private static void benchmarkRemoveOperation(Map<String, Employee> employees) {
        Map<String, Employee> hashMap = new HashMap<>(employees);
        Map<String, Employee> linkedHashMap = new LinkedHashMap<>(employees);
        Map<String, Employee> treeMap = new TreeMap<>(employees);
        Map<String, Employee> concurrentHashMap = new ConcurrentHashMap<>(employees);

        System.out.println("\nRemove Operation:");

        // HashMap Remove
        long hashMapTime = measureTime(() -> {
            for (Employee employee : employees.values()) {
                hashMap.remove(employee.getName());
            }
        });
        System.out.println("HashMap (remove): " + hashMapTime + " ms");

        // LinkedHashMap Remove
        long linkedHashMapTime = measureTime(() -> {
            for (Employee employee : employees.values()) {
                linkedHashMap.remove(employee.getName());
            }
        });
        System.out.println("LinkedHashMap (remove): " + linkedHashMapTime + " ms");

        // TreeMap Remove
        long treeMapTime = measureTime(() -> {
            for (Employee employee : employees.values()) {
                treeMap.remove(employee.getName());
            }
        });
        System.out.println("TreeMap (remove): " + treeMapTime + " ms");

        // ConcurrentHashMap Remove
        long concurrentHashMapTime = measureTime(() -> {
            for (Employee employee : employees.values()) {
                concurrentHashMap.remove(employee.getName());
            }
        });
        System.out.println("ConcurrentHashMap (remove): " + concurrentHashMapTime + " ms");
    }

    /**
     * Benchmark Iteration Operation (iteration over entries).
     */
    private static void benchmarkIterationOperation(Map<String, Employee> employees) {
        Map<String, Employee> hashMap = new HashMap<>(employees);
        Map<String, Employee> linkedHashMap = new LinkedHashMap<>(employees);
        Map<String, Employee> treeMap = new TreeMap<>(employees);
        Map<String, Employee> concurrentHashMap = new ConcurrentHashMap<>(employees);

        System.out.println("\nIteration Operation:");

        // HashMap Iteration
        long hashMapTime = measureTime(() -> {
            for (Map.Entry<String, Employee> entry : hashMap.entrySet()) {
                entry.getKey();
            }
        });
        System.out.println("HashMap (iteration): " + hashMapTime + " ms");

        // LinkedHashMap Iteration
        long linkedHashMapTime = measureTime(() -> {
            for (Map.Entry<String, Employee> entry : linkedHashMap.entrySet()) {
                entry.getKey();
            }
        });
        System.out.println("LinkedHashMap (iteration): " + linkedHashMapTime + " ms");

        // TreeMap Iteration
        long treeMapTime = measureTime(() -> {
            for (Map.Entry<String, Employee> entry : treeMap.entrySet()) {
                entry.getKey();
            }
        });
        System.out.println("TreeMap (iteration): " + treeMapTime + " ms");

        // ConcurrentHashMap Iteration
        long concurrentHashMapTime = measureTime(() -> {
            for (Map.Entry<String, Employee> entry : concurrentHashMap.entrySet()) {
                entry.getKey();
            }
        });
        System.out.println("ConcurrentHashMap (iteration): " + concurrentHashMapTime + " ms");
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