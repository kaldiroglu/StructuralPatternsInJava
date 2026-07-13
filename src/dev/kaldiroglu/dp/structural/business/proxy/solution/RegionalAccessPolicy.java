package dev.kaldiroglu.dp.structural.business.proxy.solution;

public class RegionalAccessPolicy implements AccessPolicy {

    @Override
    public boolean canRead(Caller caller, String customerId) {
        if ("admin".equals(caller.role())) return true;
        String customerRegion = customerId.startsWith("EU") ? "EU" : "US";
        return caller.region().equals(customerRegion);
    }
}
