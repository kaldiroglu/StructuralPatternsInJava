package dev.kaldiroglu.dp.structural.business.bridge.solution;

/** A multi-channel address book entry. */
public record Recipient(String email, String phone, String slackChannel, String pushDeviceToken) {}
