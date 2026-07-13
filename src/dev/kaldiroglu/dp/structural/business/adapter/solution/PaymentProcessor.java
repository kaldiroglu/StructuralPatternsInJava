package dev.kaldiroglu.dp.structural.business.adapter.solution;

public interface PaymentProcessor {
    PaymentResult charge(PaymentRequest request);
}
