package dev.kaldiroglu.dp.structural.business.proxy.problem;

import java.util.concurrent.atomic.AtomicInteger;

/** Slow, read-heavy backend. No auth, no cache. Callers improvise. */
public class CustomerProfileService {

    private final AtomicInteger backendCalls = new AtomicInteger();

    public CustomerProfile getProfile(String customerId) {
        backendCalls.incrementAndGet();
        // pretend this is a slow remote call
        return new CustomerProfile(customerId, "Customer-" + customerId,
            customerId.startsWith("EU") ? "EU" : "US");
    }

    public int backendCalls() { return backendCalls.get(); }
}
