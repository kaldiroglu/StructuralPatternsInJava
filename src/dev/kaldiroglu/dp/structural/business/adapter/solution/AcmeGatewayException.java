package dev.kaldiroglu.dp.structural.business.adapter.solution;

public class AcmeGatewayException extends Exception {
    private final int code;

    public AcmeGatewayException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() { return code; }
}
