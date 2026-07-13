package dev.kaldiroglu.dp.structural.business.bridge.solution;

import java.util.List;

public class OrderConfirmation extends Notification {

    private final String orderId;
    private final List<String> lineItems;
    private final double total;

    public OrderConfirmation(DeliveryChannel channel, String orderId,
                             List<String> lineItems, double total) {
        super(channel);
        this.orderId = orderId;
        this.lineItems = lineItems;
        this.total = total;
    }

    @Override
    protected RenderedMessage render(DeliveryChannel channel) {
        String subject = "Order " + orderId + " confirmed";
        if (channel.supportsRichContent()) {
            String items = String.join("</li><li>", lineItems);
            String body = "<h1>Thanks!</h1><ul><li>" + items + "</li></ul><p>Total: $" + total + "</p>";
            return new RenderedMessage(subject, body);
        }
        // SMS-style: short, the *abstraction* decides what's safe to drop.
        return new RenderedMessage(subject, "Order " + orderId + " confirmed. Total $" + total);
    }
}
