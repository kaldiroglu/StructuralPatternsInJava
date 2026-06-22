/**
 * FLYWEIGHT — Database Connection Pool
 *
 * REAL-WORLD SYSTEM: A database connection pool (HikariCP, Tomcat JDBC pool,
 * C3P0). Creating a TCP connection, performing a TLS handshake, and
 * authenticating to PostgreSQL takes ~5-10ms. A web request that needs three
 * queries would spend 15-30ms just connecting. The pool keeps a small set of
 * pre-established connections and reuses them across thousands of requests.
 *
 * Intrinsic state (shared, immutable): JDBC URL, credentials, pool size.
 * Extrinsic state (per-use, transient): the current transaction, the statement
 * being executed, the result set.
 *
 * SHAPE OF THE PROBLEM: You have many clients that need a small number of
 * expensive-to-create objects. Share them. The clients temporarily "borrow"
 * an object, use it, and return it.
 */
package com.structural.flyweight;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

// ── The flyweight object — expensive to create ───────────────────────
class PooledConnection implements AutoCloseable {
    private static final AtomicInteger SEQUENCE = new AtomicInteger(1);
    private final int id;
    private boolean inUse;

    public PooledConnection() {
        this.id = SEQUENCE.getAndIncrement();
        // Real system: TCP connect + TLS handshake + PostgreSQL auth
        System.out.println("  [pool] Created connection #" + id + " (expensive!)");
    }

    public int id() { return id; }
    public boolean inUse() { return inUse; }

    public void markInUse() { this.inUse = true; }
    public void markFree()   { this.inUse = false; }

    public String query(String sql) {
        // Real system: send query over TCP, read result set
        return "result of '" + sql + "' via conn #" + id;
    }

    @Override public void close() { markFree(); }
}

// ── Flyweight factory: the connection pool ───────────────────────────
class ConnectionPool {
    private final BlockingQueue<PooledConnection> available;

    public ConnectionPool(int size) {
        this.available = new LinkedBlockingQueue<>(size);
        for (int i = 0; i < size; i++) {
            available.add(new PooledConnection());
        }
        System.out.println("  [pool] Initialized with " + size + " connections\n");
    }

    public PooledConnection borrow(long timeoutMs) throws InterruptedException {
        PooledConnection conn = available.poll(timeoutMs, TimeUnit.MILLISECONDS);
        if (conn != null) conn.markInUse();
        return conn;
    }

    public void release(PooledConnection conn) {
        conn.markFree();
        available.add(conn);
    }

    public int availableCount() { return available.size(); }
}

// ── Demo: 10 concurrent "requests" compete for 3 connections ─────────
public class ConnectionPoolFlyweight {
    public static void main(String[] args) throws Exception {
        var pool = new ConnectionPool(3);

        try (var executor = Executors.newFixedThreadPool(10)) {
            for (int i = 0; i < 10; i++) {
                final int reqId = i;
                executor.submit(() -> {
                    try {
                        PooledConnection conn = pool.borrow(500);
                        if (conn == null) {
                            System.out.printf("Request #%d TIMED OUT waiting for connection%n", reqId);
                            return;
                        }
                        System.out.printf("Request #%d got conn #%d (available: %d)%n",
                            reqId, conn.id(), pool.availableCount());
                        System.out.printf("  → %s%n", conn.query("SELECT * FROM orders WHERE id=" + reqId));
                        Thread.sleep(50); // simulate query time
                        pool.release(conn);
                        System.out.printf("Request #%d released conn #%d (available: %d)%n",
                            reqId, conn.id(), pool.availableCount());
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }
        }
    }
}
