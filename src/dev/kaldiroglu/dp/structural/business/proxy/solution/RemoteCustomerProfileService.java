package dev.kaldiroglu.dp.structural.business.proxy.solution;

import java.util.concurrent.atomic.AtomicInteger;

public class RemoteCustomerProfileService implements CustomerProfileService {

    private final AtomicInteger backendCalls = new AtomicInteger();

    @Override
    public CustomerProfile getProfile(Caller caller, String customerId) {
        backendCalls.incrementAndGet();
        return new CustomerProfile(customerId, "Customer-" + customerId,
            customerId.startsWith("EU") ? "EU" : "US");
    }

    public int backendCalls() { return backendCalls.get(); }
}
