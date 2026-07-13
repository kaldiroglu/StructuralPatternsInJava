package dev.kaldiroglu.dp.structural.business.adapter.solution;

public class CheckoutService {

    private final PaymentProcessor processor;

    public CheckoutService(PaymentProcessor processor) {
        this.processor = processor;
    }

    public PaymentResult checkout(PaymentRequest request) {
        return processor.charge(request);
    }
}
