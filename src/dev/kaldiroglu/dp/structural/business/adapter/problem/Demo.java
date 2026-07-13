package dev.kaldiroglu.dp.structural.business.adapter.problem;

import java.math.BigDecimal;
import java.util.UUID;

public class Demo {
    public static void main(String[] args) {
        CheckoutService checkout = new CheckoutService(new AcmeGatewayClient());

        System.out.println(checkout.checkout(UUID.randomUUID(), "cust-1", new BigDecimal("49.95"), "USD"));
        System.out.println(checkout.checkout(UUID.randomUUID(), "BAD",    new BigDecimal("10.00"), "USD"));
        System.out.println(checkout.checkout(UUID.randomUUID(), "cust-2", new BigDecimal("0.00"),  "USD"));
    }
}
