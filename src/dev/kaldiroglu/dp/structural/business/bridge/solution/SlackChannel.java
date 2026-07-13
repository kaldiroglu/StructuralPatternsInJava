package dev.kaldiroglu.dp.structural.business.bridge.solution;

public class SlackChannel implements DeliveryChannel {
    @Override public boolean supportsRichContent() { return true; }
    @Override public int maxBodyLength() { return 40_000; }

    @Override
    public void deliver(Recipient r, RenderedMessage m) {
        System.out.println("SLACK to=" + r.slackChannel()
            + " blocks=[" + m.subject() + " | " + m.body() + "]");
    }
}
