package dev.kaldiroglu.dp.structural.facade.gof;

/** Subsystem: RISC-specific code generation. */
public class RISCCodeGenerator extends CodeGenerator {

    @Override
    public void visit(ProgramNode node) {
        if (node.getType().startsWith("var:")) {
            String name = node.getType().substring(4);
            output.emit("LOADI  R1, " + node.getValue());
            output.emit("STORE  R1, _" + name);
        } else if (node.getType().equals("expr")) {
            output.emit("EVAL   " + node.getValue());
            output.emit("NOP");
        }
    }
}
