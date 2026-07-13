package dev.kaldiroglu.dp.structural.business.adapter.solution;

public sealed interface PaymentResult {

    record Success(String authorizationId) implements PaymentResult {}

    record Failure(Reason reason, String detail) implements PaymentResult {}

    enum Reason {
        CUSTOMER_BLOCKED,
        INVALID_AMOUNT,
        CAPTURE_FAILED,
        GATEWAY_ERROR
    }
}
