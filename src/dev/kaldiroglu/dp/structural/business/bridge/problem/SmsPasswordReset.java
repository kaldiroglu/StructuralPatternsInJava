package dev.kaldiroglu.dp.structural.business.bridge.problem;

public class SmsPasswordReset {
    public void send(String phone, String resetLink) {
        System.out.println("SMS to=" + phone + " text='Reset: " + resetLink + "'");
    }
}
