package dev.kaldiroglu.dp.structural.business.proxy.solution;

public interface AccessPolicy {
    boolean canRead(Caller caller, String customerId);
}
