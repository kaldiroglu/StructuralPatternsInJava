package dev.kaldiroglu.dp.structural.business.bridge.solution;

public interface DeliveryChannel {

    /** Channel-agnostic capabilities the abstraction queries before rendering. */
    boolean supportsRichContent();

    int maxBodyLength();

    void deliver(Recipient recipient, RenderedMessage message);
}
