package dev.kaldiroglu.dp.structural.business.proxy.solution;

/** Identity of the caller, used for authorization decisions. */
public record Caller(String userId, String role, String region) {}
