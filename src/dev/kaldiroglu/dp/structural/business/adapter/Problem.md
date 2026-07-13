# Adapter — Payment Gateway Integration

## Scenario

A `CheckoutService` charges customers through a `PaymentProcessor` interface that its rest of the codebase already knows how to consume:

```java
PaymentResult result = paymentProcessor.charge(PaymentRequest req);
```

`PaymentRequest` carries a `Money` value object (currency + decimal amount), an idempotency key, customer reference, and metadata. `PaymentResult` is a sealed type returning either a successful authorization id or a typed failure.

We just signed a deal with **Acme Pay**, whose Java SDK is, charitably, "of its time":

- Amounts are `long` cents.
- It exposes a **two-step** flow: `authorize(...)` returns an auth token; `capture(authToken, ...)` settles the funds. Our codebase wants a single call.
- It throws checked `AcmeGatewayException` with integer error codes — no enum, no typed reasons.
- Idempotency is per-method, with a different field name (`requestUid`) and only on `authorize`.
- It returns `String` ids; we use `UUID`.

We don't own the SDK and won't change `CheckoutService`.

## Without the pattern

`CheckoutService` would learn about `AcmeGatewayClient`, the cents conversion, the auth/capture orchestration, and the error-code translation. Tomorrow when we add **Stripe** or **Adyen**, every one of those concerns leaks into checkout again. Conditional logic spreads, two-phase orchestration is repeated, and an SDK quirk in one provider corrupts the domain model.

See `problem/CheckoutService.java` — note the integer codes, the cents math, and the manual two-step call right inside the checkout path.

## With the Adapter pattern

We introduce `AcmePayAdapter implements PaymentProcessor`. It owns:

- **Amount conversion** — `BigDecimal` ↔ `long` cents, currency-aware (JPY has no minor units, etc.)
- **Two-step orchestration** — single `charge()` calls `authorize` then `capture`, releasing the auth on capture failure.
- **Error translation** — `AcmeGatewayException(code)` becomes a typed `PaymentResult.Failure(reason)`.
- **Idempotency mapping** — domain idempotency key → `requestUid` on `authorize`.

`CheckoutService` remains pure. To onboard a second gateway tomorrow, write one more adapter — nothing in checkout changes.

See `solution/`.
