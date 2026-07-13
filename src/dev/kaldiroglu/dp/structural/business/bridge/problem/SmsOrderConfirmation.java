package dev.kaldiroglu.dp.structural.business.bridge.problem;

public class SmsOrderConfirmation {
    public void send(String phone, String orderId, double total) {
        String text = "Order " + orderId + " confirmed. Total $" + total;
        System.out.println("SMS to=" + phone + " text='" + text + "'");
    }
}
