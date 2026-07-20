package dev.kaldiroglu.dp.structural.facade.gof;

/** Subsystem: token type produced by the Scanner. */
public class Token {
    public enum Type { KEYWORD, IDENTIFIER, NUMBER, OPERATOR, EOF }
    public final Type type;
    public final String value;

    public Token(Type type, String value) {
        this.type = type; this.value = value;
    }
    @Override public String toString() { return type + "('" + value + "')"; }
}
