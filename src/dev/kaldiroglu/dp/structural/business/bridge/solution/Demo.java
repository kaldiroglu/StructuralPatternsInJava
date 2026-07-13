package dev.kaldiroglu.dp.structural.business.bridge.solution;

import java.util.List;

public class Demo {
    public static void main(String[] args) {
        Recipient ada = new Recipient("ada@example.com", "+15555550100", "#sales", "tok-abc");

        // Same notification, three channels — no SmsOrderConfirmation, no SlackOrderConfirmation.
        new OrderConfirmation(new EmailChannel(), "ORD-1", List.of("Widget", "Gadget"), 49.95).send(ada);
        new OrderConfirmation(new SmsChannel(),   "ORD-1", List.of("Widget", "Gadget"), 49.95).send(ada);
        new OrderConfirmation(new SlackChannel(), "ORD-1", List.of("Widget", "Gadget"), 49.95).send(ada);

        // Same channel, two notifications — clean reuse.
        new PasswordReset(new EmailChannel(), "https://example.com/reset/abc").send(ada);
        new PasswordReset(new SmsChannel(),   "https://example.com/reset/abc").send(ada);
    }
}
