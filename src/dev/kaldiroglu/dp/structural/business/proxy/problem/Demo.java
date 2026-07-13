package dev.kaldiroglu.dp.structural.business.proxy.problem;

public class Demo {
    public static void main(String[] args) {
        CustomerProfileService backend = new CustomerProfileService();
        SupportConsole console = new SupportConsole(backend);

        System.out.println(console.lookup("US", "US-1001"));
        System.out.println(console.lookup("US", "US-1001")); // cached, but only in this caller
        try {
            console.lookup("US", "EU-2002");
        } catch (SecurityException e) {
            System.out.println("denied: " + e.getMessage());
        }
        System.out.println("backend calls: " + backend.backendCalls());
    }
}
