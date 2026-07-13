package dev.kaldiroglu.dp.structural.business.facade.solution;

public sealed interface CheckoutResult {

    record Placed(String orderId) implements CheckoutResult {}

    record Failed(Reason reason, String detail) implements CheckoutResult {}

    enum Reason { OUT_OF_STOCK, PAYMENT_DECLINED, UNDELIVERABLE, UNKNOWN }
}
