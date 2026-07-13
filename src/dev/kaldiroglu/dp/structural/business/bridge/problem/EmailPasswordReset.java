package dev.kaldiroglu.dp.structural.business.bridge.problem;

public class EmailPasswordReset {
    public void send(String to, String resetLink) {
        System.out.println("EMAIL to=" + to + " subject='Reset your password'"
            + " body='<a href=\"" + resetLink + "\">Click to reset</a>'");
    }
}
