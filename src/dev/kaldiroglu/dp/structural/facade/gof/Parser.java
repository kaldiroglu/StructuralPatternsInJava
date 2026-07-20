package dev.kaldiroglu.dp.structural.facade.gof;

import java.util.List;

/** Subsystem: syntactic analyser — produces AST from tokens. */
public class Parser {
    private final Scanner scanner;
    private ProgramNodeBuilder builder;

    public Parser(Scanner scanner) {
        this.scanner = scanner;
    }

    public void parse(String source, ProgramNodeBuilder builder) {
        this.builder = builder;
        List<Token> tokens = scanner.tokenize(source);
        System.out.println("[PARSER] Building AST from " + tokens.size() + " tokens");

        // Naive parse: first identifier + '=' + second token → variable assignment
        for (int i = 0; i < tokens.size() - 2; i++) {
            if (tokens.get(i).type == Token.Type.IDENTIFIER
                    && tokens.get(i + 1).type == Token.Type.OPERATOR
                    && tokens.get(i + 1).value.equals("=")) {
                String name = tokens.get(i).value;
                String value = tokens.get(i + 2).value;
                builder.newVariable(name, value);
                break;
            }
        }
    }
}
