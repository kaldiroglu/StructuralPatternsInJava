package dev.kaldiroglu.dp.structural.business.bridge.problem;

public class Demo {
    public static void main(String[] args) {
        new EmailOrderConfirmation().send("a@example.com", "ORD-1", 49.95);
        new SmsOrderConfirmation().send("+15555550100", "ORD-1", 49.95);
        new SlackOrderConfirmation().send("#sales", "ORD-1", 49.95);
        new EmailPasswordReset().send("a@example.com", "https://example.com/reset/abc");
        new SmsPasswordReset().send("+15555550100", "https://example.com/reset/abc");
        // SlackPasswordReset? we forgot. Adding marketing campaigns? 3 more classes.
    }
}
