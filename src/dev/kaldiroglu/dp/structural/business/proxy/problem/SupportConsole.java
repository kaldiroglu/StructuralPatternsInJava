package dev.kaldiroglu.dp.structural.business.proxy.problem;

import java.util.HashMap;
import java.util.Map;

/**
 * Caller does its own auth and its own caching. Other callers will have a
 * different idea of access rules and will hammer the backend separately.
 */
public class SupportConsole {

    private final CustomerProfileService service;
    private final Map<String, CustomerProfile> localCache = new HashMap<>();

    public SupportConsole(CustomerProfileService service) {
        this.service = service;
    }

    public CustomerProfile lookup(String agentRegion, String customerId) {
        if (localCache.containsKey(customerId)) return localCache.get(customerId);

        // crude inline auth
        boolean euAgent = "EU".equals(agentRegion);
        boolean euCustomer = customerId.startsWith("EU");
        if (euAgent != euCustomer) throw new SecurityException("region mismatch");

        CustomerProfile p = service.getProfile(customerId);
        localCache.put(customerId, p);
        return p;
    }
}
