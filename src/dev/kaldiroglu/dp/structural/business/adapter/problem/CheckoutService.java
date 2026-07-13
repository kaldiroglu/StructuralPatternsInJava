package dev.kaldiroglu.dp.structural.business.adapter.problem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

/**
 * SDK quirks leak straight into the checkout path: cents conversion,
 * two-step orchestration, and integer error codes are all entangled with
 * the domain logic. Onboarding a second gateway will copy all of it.
 */
public class CheckoutService {

    private final AcmeGatewayClient gateway;

    public CheckoutService(AcmeGatewayClient gateway) {
        this.gateway = gateway;
    }

    public String checkout(UUID idempotencyKey, String customerRef,
                           BigDecimal amount, String currency) {
        long cents = amount.movePointRight(2).setScale(0, RoundingMode.HALF_UP).longValueExact();
        // (note: silently wrong for currencies with 0 or 3 minor units)

        String authToken;
        try {
            authToken = gateway.authorize(idempotencyKey.toString(), customerRef, cents, currency);
        } catch (AcmeGatewayException e) {
            if (e.getCode() == 4030) return "DECLINED:CUSTOMER_BLOCKED";
            if (e.getCode() == 4001) return "DECLINED:INVALID_AMOUNT";
            return "DECLINED:UNKNOWN(" + e.getCode() + ")";
        }

        try {
            String captureId = gateway.capture(authToken, cents);
            return "PAID:" + captureId;
        } catch (AcmeGatewayException e) {
            gateway.release(authToken); // hope this works
            return "DECLINED:CAPTURE_FAILED(" + e.getCode() + ")";
        }
    }
}
