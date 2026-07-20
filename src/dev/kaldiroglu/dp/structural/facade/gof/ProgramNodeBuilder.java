package dev.kaldiroglu.dp.structural.facade.gof;

import java.util.ArrayList;
import java.util.List;

/** Subsystem: incremental builder for the AST. */
public class ProgramNodeBuilder {
    private final List<ProgramNode> nodes = new ArrayList<>();

    public ProgramNode newVariable(String name, String value) {
        ProgramNode node = new ProgramNode("var:" + name, value);
        nodes.add(node);
        System.out.println("[NODE-BUILDER] Created " + node);
        return node;
    }

    public ProgramNode newExpression(String left, String operator, String right) {
        ProgramNode node = new ProgramNode("expr", left + " " + operator + " " + right);
        nodes.add(node);
        System.out.println("[NODE-BUILDER] Created " + node);
        return node;
    }

    public ProgramNode getRootNode() {
        return nodes.isEmpty() ? null : nodes.get(0);
    }
}
