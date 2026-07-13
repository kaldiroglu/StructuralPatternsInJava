package dev.kaldiroglu.dp.structural.business.bridge.solution;

public abstract class Notification {

    protected final DeliveryChannel channel;

    protected Notification(DeliveryChannel channel) {
        this.channel = channel;
    }

    /** Subclasses know what they're saying; channel decides format constraints. */
    protected abstract RenderedMessage render(DeliveryChannel channel);

    public final void send(Recipient recipient) {
        RenderedMessage rendered = render(channel);
        if (rendered.body().length() > channel.maxBodyLength()) {
            throw new IllegalStateException(
                "Renderer overflowed channel limit: " + rendered.body().length()
                    + " > " + channel.maxBodyLength());
        }
        channel.deliver(recipient, rendered);
    }
}
