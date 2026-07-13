package dev.kaldiroglu.dp.structural.business.adapter.solution;

import java.math.BigDecimal;
import java.util.UUID;

public class Demo {
    public static void main(String[] args) {
        PaymentProcessor processor = new AcmePayAdapter(new AcmeGatewayClient());
        CheckoutService checkout = new CheckoutService(processor);

        print(checkout.checkout(new PaymentRequest(
            UUID.randomUUID(), "cust-1", new Money(new BigDecimal("49.95"), "USD"))));

        print(checkout.checkout(new PaymentRequest(
            UUID.randomUUID(), "cust-jp", new Money(new BigDecimal("1500"), "JPY"))));

        print(checkout.checkout(new PaymentRequest(
            UUID.randomUUID(), "BAD", new Money(new BigDecimal("10.00"), "USD"))));
    }

    private static void print(PaymentResult r) {
        System.out.println(switch (r) {
            case PaymentResult.Success s    -> "PAID:" + s.authorizationId();
            case PaymentResult.Failure f    -> "DECLINED:" + f.reason() + " (" + f.detail() + ")";
        });
    }
}
