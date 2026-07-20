package dev.kaldiroglu.dp.structural.facade.gof;

/** Subsystem: memory buffer that holds emitted bytecode. */
public class BytecodeStream {
    private final StringBuilder buffer = new StringBuilder();

    public void emit(String instruction) {
        buffer.append(instruction).append("\n");
        System.out.println("[BYTECODE] " + instruction);
    }

    public String getBytecode() { return buffer.toString(); }
}
