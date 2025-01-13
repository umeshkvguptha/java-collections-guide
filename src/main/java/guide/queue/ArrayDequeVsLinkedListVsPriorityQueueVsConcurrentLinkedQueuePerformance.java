package guide.queue;

import guide.common.api.Employee;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.UUID;

public class ArrayDequeVsLinkedListVsPriorityQueueVsConcurrentLinkedQueuePerformance {
    private final static int SIZE = 1000_000; // Queue size for benchmarking

    public static void main(String[] args) {
        System.out.println("Performance Comparison: ArrayDeque vs LinkedList as Deque vs PriorityQueue vs ConcurrentLinkedQueue");

        // Pre-create the Employee objects
        Queue<Employee> employees = createEmployees();

        // 1. Add Operation Performance
        benchmarkAddOperation(employees);

        // 2. Remove Operation Performance
        benchmarkRemoveOperation(employees);

        // 3. Peek Operation Performance
        benchmarkPeekOperation(employees);
    }

    /**
     * Pre-creates Employee objects for benchmarking.
     */
    private static Queue<Employee> createEmployees() {
        Queue<Employee> employees = new LinkedList<>();
        for (int i = 0; i < SIZE; i++) {
            employees.add(new Employee(UUID.randomUUID(), "Employee_" + i));
        }
        return employees;
    }

    /**
     * Benchmark Add Operation.
     */
    private static void benchmarkAddOperation(Queue<Employee> employees) {
        Queue<Employee> arrayDeque = new ArrayDeque<>(SIZE);
        Queue<Employee> linkedList = new LinkedList<>();
        Queue<Employee> priorityQueue = new PriorityQueue<>((e1, e2) -> e1.getName().compareTo(e2.getName()));
        Queue<Employee> concurrentQueue = new ConcurrentLinkedQueue<>();

        System.out.println("\nAdd Operation:");

        // ArrayDeque Add
        long arrayDequeTime = measureTime(() -> {
            for (Employee employee : employees) {
                arrayDeque.add(employee);
            }
        });
        System.out.println("ArrayDeque (add): " + arrayDequeTime + " ms");

        // LinkedList Add
        long linkedListTime = measureTime(() -> {
            for (Employee employee : employees) {
                linkedList.add(employee);
            }
        });
        System.out.println("LinkedList (add): " + linkedListTime + " ms");

        // PriorityQueue Add
        long priorityQueueTime = measureTime(() -> {
            for (Employee employee : employees) {
                priorityQueue.add(employee);
            }
        });
        System.out.println("PriorityQueue (add): " + priorityQueueTime + " ms");

        // ConcurrentLinkedQueue Add
        long concurrentQueueTime = measureTime(() -> {
            for (Employee employee : employees) {
                concurrentQueue.add(employee);
            }
        });
        System.out.println("ConcurrentLinkedQueue (add): " + concurrentQueueTime + " ms");
    }

    /**
     * Benchmark Remove Operation.
     */
    private static void benchmarkRemoveOperation(Queue<Employee> employees) {
        Queue<Employee> arrayDeque = new ArrayDeque<>(employees);
        Queue<Employee> linkedList = new LinkedList<>(employees);
        Queue<Employee> priorityQueue = new PriorityQueue<>(employees);
        Queue<Employee> concurrentQueue = new ConcurrentLinkedQueue<>(employees);

        System.out.println("\nRemove Operation:");

        // ArrayDeque Remove
        long arrayDequeTime = measureTime(() -> {
            for (Employee employee : employees) {
                arrayDeque.remove();
            }
        });
        System.out.println("ArrayDeque (remove): " + arrayDequeTime + " ms");

        // LinkedList Remove
        long linkedListTime = measureTime(() -> {
            for (Employee employee : employees) {
                linkedList.remove();
            }
        });
        System.out.println("LinkedList (remove): " + linkedListTime + " ms");

        // PriorityQueue Remove
        long priorityQueueTime = measureTime(() -> {
            for (Employee employee : employees) {
                priorityQueue.remove();
            }
        });
        System.out.println("PriorityQueue (remove): " + priorityQueueTime + " ms");

        // ConcurrentLinkedQueue Remove
        long concurrentQueueTime = measureTime(() -> {
            for (Employee employee : employees) {
                concurrentQueue.poll();
            }
        });
        System.out.println("ConcurrentLinkedQueue (remove): " + concurrentQueueTime + " ms");
    }

    /**
     * Benchmark Peek Operation.
     */
    private static void benchmarkPeekOperation(Queue<Employee> employees) {
        Queue<Employee> arrayDeque = new ArrayDeque<>(employees);
        Queue<Employee> linkedList = new LinkedList<>(employees);
        Queue<Employee> priorityQueue = new PriorityQueue<>(employees);
        Queue<Employee> concurrentQueue = new ConcurrentLinkedQueue<>(employees);

        System.out.println("\nPeek Operation:");

        // ArrayDeque Peek
        long arrayDequeTime = measureTime(() -> {
            for (Employee employee : employees) {
                arrayDeque.peek();
            }
        });
        System.out.println("ArrayDeque (peek): " + arrayDequeTime + " ms");

        // LinkedList Peek
        long linkedListTime = measureTime(() -> {
            for (Employee employee : employees) {
                linkedList.peek();
            }
        });
        System.out.println("LinkedList (peek): " + linkedListTime + " ms");

        // PriorityQueue Peek
        long priorityQueueTime = measureTime(() -> {
            for (Employee employee : employees) {
                priorityQueue.peek();
            }
        });
        System.out.println("PriorityQueue (peek): " + priorityQueueTime + " ms");

        // ConcurrentLinkedQueue Peek
        long concurrentQueueTime = measureTime(() -> {
            for (Employee employee : employees) {
                concurrentQueue.peek();
            }
        });
        System.out.println("ConcurrentLinkedQueue (peek): " + concurrentQueueTime + " ms");
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