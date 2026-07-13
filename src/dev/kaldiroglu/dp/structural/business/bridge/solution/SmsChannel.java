package dev.kaldiroglu.dp.structural.business.bridge.solution;

public class SmsChannel implements DeliveryChannel {
    @Override public boolean supportsRichContent() { return false; }
    @Override public int maxBodyLength() { return 160; }

    @Override
    public void deliver(Recipient r, RenderedMessage m) {
        System.out.println("SMS to=" + r.phone() + " text='" + m.body() + "'");
    }
}
