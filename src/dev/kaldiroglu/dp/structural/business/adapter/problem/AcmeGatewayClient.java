package dev.kaldiroglu.dp.structural.business.adapter.problem;

/**
 * Third-party SDK we do not own. Two-step authorize/capture flow,
 * cents-as-long amounts, integer error codes, "requestUid" idempotency.
 */
public class AcmeGatewayClient {

    public String authorize(String requestUid, String customerRef, long amountCents, String currency)
            throws AcmeGatewayException {
        if (amountCents <= 0) throw new AcmeGatewayException(4001, "amount must be positive");
        if ("BAD".equals(customerRef)) throw new AcmeGatewayException(4030, "customer blocked");
        return "AUTH-" + requestUid;
    }

    public String capture(String authToken, long amountCents) throws AcmeGatewayException {
        if (authToken == null || !authToken.startsWith("AUTH-"))
            throw new AcmeGatewayException(4002, "invalid auth token");
        return "CAP-" + authToken.substring(5);
    }

    public void release(String authToken) {
        // best-effort void of an unused authorization
    }
}
