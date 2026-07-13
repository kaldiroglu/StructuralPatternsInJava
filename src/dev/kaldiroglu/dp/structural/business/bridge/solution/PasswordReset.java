package dev.kaldiroglu.dp.structural.business.bridge.solution;

public class PasswordReset extends Notification {

    private final String resetLink;

    public PasswordReset(DeliveryChannel channel, String resetLink) {
        super(channel);
        this.resetLink = resetLink;
    }

    @Override
    protected RenderedMessage render(DeliveryChannel channel) {
        String subject = "Reset your password";
        if (channel.supportsRichContent()) {
            return new RenderedMessage(subject,
                "<a href=\"" + resetLink + "\">Click to reset</a>");
        }
        return new RenderedMessage(subject, "Reset: " + resetLink);
    }
}
