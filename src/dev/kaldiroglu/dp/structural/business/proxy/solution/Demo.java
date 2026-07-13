package dev.kaldiroglu.dp.structural.business.proxy.solution;

import java.time.Duration;

public class Demo {
    public static void main(String[] args) {
        RemoteCustomerProfileService backend = new RemoteCustomerProfileService();

        CustomerProfileService service =
            new AuthorizationProxy(
                new CachingProxy(backend, Duration.ofMinutes(5)),
                new RegionalAccessPolicy());

        Caller usAgent = new Caller("agent-1", "support", "US");
        Caller euAgent = new Caller("agent-2", "support", "EU");
        Caller admin   = new Caller("root",    "admin",   "US");

        System.out.println(service.getProfile(usAgent, "US-1001"));
        System.out.println(service.getProfile(usAgent, "US-1001")); // cached
        System.out.println(service.getProfile(admin,   "US-1001")); // also cached, admin allowed

        try {
            service.getProfile(usAgent, "EU-2002");
        } catch (SecurityException e) {
            System.out.println("denied: " + e.getMessage());
        }

        System.out.println(service.getProfile(euAgent, "EU-2002")); // backend hit
        System.out.println(service.getProfile(admin,   "EU-2002")); // cache hit

        System.out.println("backend calls: " + backend.backendCalls());
    }
}
