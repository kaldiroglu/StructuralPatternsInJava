package dev.kaldiroglu.dp.structural.facade.gof;

import java.util.Arrays;
import java.util.List;

/** Subsystem: lexical analyser — turns source text into tokens. */
public class Scanner {
    public List<Token> tokenize(String source) {
        System.out.println("[SCANNER] Tokenizing: " + source.substring(0, Math.min(source.length(), 30)));
        return Arrays.asList(
            new Token(Token.Type.KEYWORD, "int"),
            new Token(Token.Type.IDENTIFIER, "x"),
            new Token(Token.Type.OPERATOR, "="),
            new Token(Token.Type.NUMBER, "42"),
            new Token(Token.Type.EOF, ""));
    }
}
