# 30-question Java backend interview set

**Source note:** Topic list adapted from a user-provided social-media screenshot; it is not verified as an official Morgan Stanley interview question list. Answers below are original educational explanations.

Use the [study notes](../index.md) first. These are **compact revision answers**, not substitutes for chapter-level understanding.

## Core Java

### 1. How does HashMap work internally?

**Interview answer:** It hashes the key to choose a bucket, compares keys with equals(), handles collisions within the bucket, and resizes as needed. Expected lookup is O(1).

**Follow-up / production angle:** See the full HashMap study chapter.

### 2. ArrayList vs LinkedList?

**Interview answer:** ArrayList uses a growable array, giving O(1) indexed access and usually better memory locality. LinkedList is doubly linked: indexed lookup is O(n); inserting/removing via a known node/iterator is efficient, but finding the position is not.

**Follow-up / production angle:** Choose ArrayList by default unless you have evidence another structure is better.

### 3. HashMap vs ConcurrentHashMap?

**Interview answer:** HashMap does not support unsynchronized concurrent structural updates. ConcurrentHashMap supports concurrent access and atomic operations such as computeIfAbsent. It does not accept null keys or values.

**Follow-up / production angle:** Do not assume a get-then-put sequence is atomic; use atomic methods.

### 4. How do you create an immutable class?

**Interview answer:** Make fields private and final, prevent mutators, validate constructor input, and defensively copy mutable inputs and outputs. final class prevents subclass-based mutation.

**Follow-up / production angle:** Records are shallowly immutable; their mutable components still need copying.

### 5. Comparable vs Comparator?

**Interview answer:** Comparable defines a type’s natural order with compareTo(). Comparator is a separate ordering strategy using compare().

**Follow-up / production angle:** For multi-field sorting, use Comparator.comparing(...).thenComparing(...).

## Multithreading

### 6. synchronized vs volatile?

**Interview answer:** volatile ensures visibility and ordering guarantees for a variable, but does not make increment atomic. synchronized provides mutual exclusion and happens-before guarantees around monitor entry/exit.

**Follow-up / production angle:** For counters use AtomicInteger, synchronization, or an appropriate concurrent design.

### 7. How do you prevent deadlocks?

**Interview answer:** Use a consistent lock order, avoid holding locks over slow calls, minimize nested locks, and consider timed tryLock with recovery logic.

**Follow-up / production angle:** Diagnose with thread dumps and blocked-thread analysis.

### 8. Thread vs Runnable vs Callable?

**Interview answer:** Thread is an execution object. Runnable is a task with no return value and cannot declare checked exceptions in run(). Callable<V> returns a result and may throw checked exceptions.

**Follow-up / production angle:** Prefer submitting tasks to executors over manually managing many threads.

### 9. How does ExecutorService work?

**Interview answer:** It manages task execution through a pool or other execution strategy and returns Future values for submitted tasks. Queueing, pool size, rejection and shutdown policies affect behavior.

**Follow-up / production angle:** Always define shutdown, bounded resources and rejection behavior for production.

## Java 8

### 10. map() vs flatMap()?

**Interview answer:** map transforms each element to one output value; flatMap maps each element to a stream and flattens those streams into one.

**Follow-up / production angle:** Example: stream of lists → flatMap(List::stream) → stream of elements.

### 11. Find duplicate elements using Streams.

**Interview answer:** Group elements by identity and count occurrences, then filter counts greater than one; alternatively use a Set to detect repeats in a sequential stream.

**Follow-up / production angle:** Consider stable output order, nulls, and parallel-stream behavior.

### 12. How does CompletableFuture work?

**Interview answer:** It represents a value that may complete later and supports dependent async stages using thenApply, thenCompose, thenCombine, and error handling.

**Follow-up / production angle:** Use explicit executors for controlled workloads; avoid blocking join() in the middle of async pipelines.

## Spring Boot

### 13. How does auto-configuration work?

**Interview answer:** Spring Boot discovers auto-configuration classes and conditionally creates beans based on classpath, properties and existing beans.

**Follow-up / production angle:** Inspect the condition evaluation report when a bean is unexpectedly configured.

### 14. How does @Transactional work?

**Interview answer:** Spring normally applies transactional behavior through AOP proxies around eligible method calls, opening/joining a transaction and committing or rolling back according to rules.

**Follow-up / production angle:** Self-invocation bypasses proxy interception; default rollback is typically for unchecked exceptions.

### 15. @Component vs @Bean?

**Interview answer:** @Component marks a class for component scanning; @Bean declares a bean using a factory method in a configuration class or supported component.

**Follow-up / production angle:** Use @Bean when you need control over constructing a library class.

### 16. How does dependency injection work?

**Interview answer:** Spring creates and wires objects based on configuration and type/qualifier resolution. Constructor injection makes required dependencies explicit and easier to test.

**Follow-up / production angle:** Prefer constructor injection and avoid cyclic dependencies.

## Microservices

### 17. Monolith vs Microservices?

**Interview answer:** A monolith deploys as one application unit; microservices split into independently deployable services with network communication and operational overhead.

**Follow-up / production angle:** A modular monolith is often a useful middle ground.

### 18. Explain Circuit Breaker.

**Interview answer:** It tracks failed calls and transitions between closed, open and half-open states to reduce repeated calls to an unhealthy dependency.

**Follow-up / production angle:** Combine it with appropriate timeouts, metrics and selective fallbacks.

### 19. How does API Gateway work?

**Interview answer:** A gateway is an entry point that routes requests to backend services and can apply cross-cutting controls such as authentication, rate limits and observability.

**Follow-up / production angle:** Do not put all business logic into the gateway.

### 20. How do you implement distributed tracing?

**Interview answer:** Propagate trace context across HTTP and messaging boundaries, instrument services (e.g., OpenTelemetry), export spans and correlate trace IDs with logs.

**Follow-up / production angle:** Use W3C trace context; account for sampling and privacy.

## Kafka

### 21. Topic vs Partition vs Consumer Group?

**Interview answer:** A topic is a logical event stream. Partitions split its log for scale and ordering within a partition. A consumer group coordinates consumption so a partition is assigned to at most one group member at a time.

**Follow-up / production angle:** Group parallelism for one topic is bounded by assigned partitions.

### 22. How do you handle duplicate messages?

**Interview answer:** Use idempotent consumers with a durable processed-event key or unique business constraint and commit offsets only after successful processing.

**Follow-up / production angle:** Exactly-once Kafka processing does not automatically make an external database side effect exactly once.

### 23. How do you troubleshoot consumer lag?

**Interview answer:** Check lag per partition, processing time, rebalance frequency, errors, downstream database latency, consumer capacity and partition count.

**Follow-up / production angle:** Scale consumers only if partitions and downstream dependencies allow it.

## SQL & Database

### 24. Find the latest record per customer.

**Interview answer:** Use ROW_NUMBER() OVER (PARTITION BY customer_id ORDER BY created_at DESC, id DESC) and filter rn = 1.

**Follow-up / production angle:** Add a stable tie-breaker; choose indexes based on query plan.

### 25. How do you identify duplicate records?

**Interview answer:** GROUP BY the relevant business key and HAVING COUNT(*) > 1. For complete duplicate rows, join to those keys or use a window count.

**Follow-up / production angle:** Define what counts as duplicate before deleting anything.

### 26. What causes database deadlocks?

**Interview answer:** Transactions take locks in incompatible orders, creating a circular wait. The database detects a victim and rolls a transaction back.

**Follow-up / production angle:** Keep transactions short, index queries, lock in consistent order and retry appropriate failures.

## Coding & System Design

### 27. Design a scalable REST API.

**Interview answer:** Define resource contracts, authentication, idempotency, pagination, validation, rate limits, caching, observability, data partitioning and capacity targets.

**Follow-up / production angle:** Discuss SLOs, failure handling and backward compatibility.

### 28. How would you optimize a slow API?

**Interview answer:** Measure end-to-end latency, profile application code, review SQL plans, external calls, N+1 queries and GC before changing anything.

**Follow-up / production angle:** Use p95/p99 latency, traces and a repeatable load test.

### 29. Design a rate limiter.

**Interview answer:** Use a token bucket or sliding-window algorithm. For multi-instance services, centralize state or use an appropriate distributed strategy.

**Follow-up / production angle:** Clarify identity key, burst tolerance, consistency and HTTP 429 response.

### 30. How would you troubleshoot a slow batch job?

**Interview answer:** Measure throughput per stage, inspect DB plans, chunk sizes, locking, network calls, GC and failure retries; then optimize the dominant bottleneck.

**Follow-up / production angle:** Make batches restartable and idempotent where possible.

## SQL examples

**Latest row for each customer (MySQL 8+):**

```sql
WITH ranked AS (
    SELECT o.*,
           ROW_NUMBER() OVER (
               PARTITION BY customer_id
               ORDER BY created_at DESC, id DESC
           ) AS rn
    FROM orders o
)
SELECT * FROM ranked WHERE rn = 1;
```

**Find duplicate invoice numbers within one vendor:**

```sql
SELECT vendor_id, invoice_number, COUNT(*) AS occurrences
FROM invoices
GROUP BY vendor_id, invoice_number
HAVING COUNT(*) > 1;
```

**Next steps:** study one answer in depth, write or run an example, then explain the trade-off out loud.
