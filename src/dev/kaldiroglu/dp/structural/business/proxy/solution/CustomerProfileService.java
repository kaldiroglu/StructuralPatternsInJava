package dev.kaldiroglu.dp.structural.business.proxy.solution;

public interface CustomerProfileService {
    CustomerProfile getProfile(Caller caller, String customerId);
}
