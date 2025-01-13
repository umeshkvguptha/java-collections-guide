# Java Collections Guide

This repository provides a comprehensive guide to common Java collections, including examples of their usage, performance benchmarks, and guidelines on when to use each collection type.

## Overview

The collection classes we’ve explored include:
- **ArrayList**
- **LinkedList**
- **HashMap**
- **LinkedHashMap**
- **TreeMap**
- **ConcurrentHashMap**
- **ArrayDeque**
- **PriorityQueue**
- **HashSet**
- **LinkedHashSet**
- **TreeSet**
- **ConcurrentLinkedQueue**

For each collection, we demonstrate:
- How to use the collection with practical examples.
- Benchmarks to show where each collection excels and where it may underperform.
- Time and space complexities for common operations to help you choose the right collection based on your use case.

---

## List of Classes

### 1. **List Implementation**
- **`guide.list.ArrayListExample`**: Demonstrates how to use the `ArrayList` class. It includes basic operations like adding, removing, and accessing elements, showcasing its benefits in random access performance.
- **`guide.list.LinkedListExample`**: Demonstrates the usage of `LinkedList`. It highlights its strengths in insertion and removal operations, particularly at the ends.
- **`guide.list.ArrayListVsLinkedListPerformance`**: A performance benchmark comparing `ArrayList` and `LinkedList`, analyzing operations such as adding, removing, and accessing elements.

### 2. **Map Implementations**
- **`guide.map.HashMapExample`**: Demonstrates how to use `HashMap` for storing key-value pairs and accessing data with constant time complexity.
- **`guide.map.LinkedHashMapExample`**: Shows how `LinkedHashMap` preserves insertion order while maintaining O(1) access time for data retrieval.
- **`guide.map.TreeMapExample`**: Demonstrates the use of `TreeMap`, showcasing its sorted order and O(log n) time complexity for operations.
- **`guide.map.HashMapVsLinkedHashMapVsTreeMapVsConcurrentHashMapPerformence`**: A performance comparison across `HashMap`, `LinkedHashMap`, `TreeMap`, and `ConcurrentHashMap`, analyzing add, search, remove, and iteration operations.
- **`guide.map.ConcurrentHashMap`**: Demonstrates `ConcurrentHashMap` for thread-safe operations in a multi-threaded environment.

### 3. **Queue Implementations**
- **`guide.queue.ArrayDequeExample`**: Demonstrates how to use `ArrayDeque` as a queue, highlighting its fast operations for adding/removing elements from both ends.
- **`guide.queue.LinkedListAsQueueExample`**: Shows how `LinkedList` can be used as a queue, comparing it with other queue implementations.
- **`guide.queue.PriorityQueueExample`**: Demonstrates the use of `PriorityQueue`, which automatically orders elements based on their priority.
- **`guide.queue.ArrayDequeVsLinkedListVsPriorityQueueVsConcurrentLinkedQueuePerformance`**: A benchmark comparing the performance of `ArrayDeque`, `LinkedList`, `PriorityQueue`, and `ConcurrentLinkedQueue`.

### 4. **Set Implementations**
- **`guide.set.HashSetExample`**: Demonstrates the usage of `HashSet`, focusing on how it handles unique elements with O(1) time complexity for most operations.
- **`guide.set.LinkedHashSetExample`**: Shows the behavior of `LinkedHashSet`, which maintains insertion order while offering the same O(1) complexity as `HashSet`.
- **`guide.set.TreeSetExample`**: Demonstrates how `TreeSet` stores elements in sorted order with O(log n) time complexity.
- **`guide.set.TreeSetVsHashSetVsLinkedHashSetPerformance`**: A performance comparison between `TreeSet`, `HashSet`, and `LinkedHashSet`.

---

## Time and Space Complexity

| Operation                    | **ArrayList** | **LinkedList** | **HashMap** | **LinkedHashMap** | **TreeMap** | **ConcurrentHashMap** | **ArrayDeque** | **PriorityQueue** | **HashSet** | **LinkedHashSet** | **TreeSet** |
|------------------------------|---------------|----------------|-------------|-------------------|-------------|-----------------------|----------------|-------------------|-------------|-------------------|-------------|
| **Add**                       | O(1)          | O(1)           | O(1)        | O(1)              | O(log n)    | O(1)                  | O(1)           | O(log n)          | O(1)        | O(1)              | O(log n)    |
| **Remove**                    | O(n)          | O(1)           | O(1)        | O(1)              | O(log n)    | O(1)                  | O(1)           | O(log n)          | O(1)        | O(1)              | O(log n)    |
| **Access/Search**             | O(1)          | O(n)           | O(1)        | O(1)              | O(log n)    | O(1)                  | O(1)           | O(log n)          | O(1)        | O(1)              | O(log n)    |
| **Iteration**                 | O(n)          | O(n)           | O(n)        | O(n)              | O(n)        | O(n)                  | O(n)           | O(n)              | O(n)        | O(n)              | O(n)        |
| **Space Complexity**          | O(n)          | O(n)           | O(n)        | O(n)              | O(n)        | O(n)                  | O(n)           | O(n)              | O(n)        | O(n)              | O(n)        |

---

## When to Use What

- **`ArrayList`**:
    - Use when you need fast random access to elements and infrequent insertions or deletions in the middle of the list.
    - Avoid when you need to perform frequent insertions/removals, especially in the middle of the list.

- **`LinkedList`**:
    - Use when you need efficient insertions or deletions from both ends (i.e., as a queue or stack).
    - Avoid for random access, as it has O(n) time complexity for access.

- **`HashMap`**:
    - Use when you need fast lookups, insertions, and deletions by key with O(1) time complexity.
    - Avoid when you need to maintain insertion order or sort entries.

- **`LinkedHashMap`**:
    - Use when you need to maintain the insertion order of elements while benefiting from O(1) lookups.
    - Avoid when sorting is needed.

- **`TreeMap`**:
    - Use when you need to store elements in a sorted order and perform operations like range queries.
    - Avoid when performance is critical for large data sets, as it has O(log n) complexity for most operations.

- **`ConcurrentHashMap`**:
    - Use when you need thread-safe operations in a multi-threaded environment with high concurrency.
    - Avoid in single-threaded applications as it incurs additional overhead.

- **`ArrayDeque`**:
    - Use when you need a fast, efficient queue or stack implementation.
    - Avoid when you need a deque with random access.

- **`PriorityQueue`**:
    - Use when you need elements processed in priority order (e.g., in scheduling or task management).
    - Avoid when the order does not matter and performance is critical.

- **`HashSet`**:
    - Use when you need a collection that holds unique elements with fast access and insertion.
    - Avoid if you need elements in a specific order.

- **`LinkedHashSet`**:
    - Use when you need unique elements with the additional requirement of maintaining insertion order.
    - Avoid when order does not matter.

- **`TreeSet`**:
    - Use when you need unique elements stored in sorted order.
    - Avoid if the sorting order is not required, as it has O(log n) operations.

---

## Contribution Guidelines

We welcome contributions to this guide! If you would like to contribute, here’s how you can get started:

1. Fork this repository.
2. Create a branch with your changes (e.g., `feature/my-new-feature`).
3. Make your changes and ensure they are well-documented.
4. Run tests to ensure your changes do not break the existing functionality.
5. Create a pull request (PR) to merge your changes back into the main branch.

We will review your pull request and get back to you as soon as possible. Thank you for helping us improve this guide!

---

## Conclusion

This guide provides clear insights into the various Java collections, their usage, performance characteristics, and when to use them based on the complexity of the operations. Each collection is designed for specific use cases, so understanding the trade-offs will help you optimize your Java applications.
