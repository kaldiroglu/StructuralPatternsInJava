package dev.kaldiroglu.dp.structural.business.adapter.solution;

import java.util.UUID;

public record PaymentRequest(
    UUID idempotencyKey,
    String customerRef,
    Money amount
) {}
