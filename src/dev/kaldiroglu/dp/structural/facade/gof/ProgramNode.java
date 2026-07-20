package dev.kaldiroglu.dp.structural.facade.gof;

/** Subsystem: a node in the abstract syntax tree (AST). */
public class ProgramNode {
    private final String type;
    private final String value;

    public ProgramNode(String type, String value) {
        this.type = type; this.value = value;
    }

    public void traverse(CodeGenerator generator) {
        generator.visit(this);
    }

    public String getType()  { return type; }
    public String getValue() { return value; }
    @Override public String toString() { return type + ":" + value; }
}
