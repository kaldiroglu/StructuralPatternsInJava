package dev.kaldiroglu.dp.structural.business.bridge.problem;

public class SlackOrderConfirmation {
    public void send(String channel, String orderId, double total) {
        System.out.println("SLACK to=" + channel + " blocks=[Order " + orderId
            + " confirmed | total=$" + total + "]");
    }
}
