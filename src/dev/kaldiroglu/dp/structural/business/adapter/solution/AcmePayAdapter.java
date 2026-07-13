package dev.kaldiroglu.dp.structural.business.adapter.solution;

/**
 * Adapts the legacy two-step Acme SDK to our single-call PaymentProcessor.
 * Owns: amount conversion, two-step orchestration, error code translation,
 * and idempotency-key mapping. CheckoutService stays pure.
 */
public class AcmePayAdapter implements PaymentProcessor {

    private final AcmeGatewayClient client;

    public AcmePayAdapter(AcmeGatewayClient client) {
        this.client = client;
    }

    @Override
    public PaymentResult charge(PaymentRequest req) {
        long minorUnits = req.amount().toMinorUnits();
        String currency = req.amount().currency();
        String requestUid = req.idempotencyKey().toString();

        String authToken;
        try {
            authToken = client.authorize(requestUid, req.customerRef(), minorUnits, currency);
        } catch (AcmeGatewayException e) {
            return new PaymentResult.Failure(translate(e.getCode()), e.getMessage());
        }

        try {
            String captureId = client.capture(authToken, minorUnits);
            return new PaymentResult.Success(captureId);
        } catch (AcmeGatewayException e) {
            client.release(authToken);
            return new PaymentResult.Failure(PaymentResult.Reason.CAPTURE_FAILED, e.getMessage());
        }
    }

    private static PaymentResult.Reason translate(int code) {
        return switch (code) {
            case 4001 -> PaymentResult.Reason.INVALID_AMOUNT;
            case 4030 -> PaymentResult.Reason.CUSTOMER_BLOCKED;
            default   -> PaymentResult.Reason.GATEWAY_ERROR;
        };
    }
}
