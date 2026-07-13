package dev.kaldiroglu.dp.structural.business.bridge.problem;

public class EmailOrderConfirmation {
    public void send(String to, String orderId, double total) {
        System.out.println("EMAIL to=" + to + " subject='Order " + orderId
            + " confirmed' body='<h1>Thanks!</h1><p>Total: $" + total + "</p>'");
    }
}
