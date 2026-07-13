package dev.kaldiroglu.dp.structural.business.bridge.solution;

public class EmailChannel implements DeliveryChannel {
    @Override public boolean supportsRichContent() { return true; }
    @Override public int maxBodyLength() { return 1_000_000; }

    @Override
    public void deliver(Recipient r, RenderedMessage m) {
        System.out.println("EMAIL to=" + r.email()
            + " subject='" + m.subject() + "' body='" + m.body() + "'");
    }
}
