package dev.kaldiroglu.dp.structural.facade.gof;

/**
 * AFTER the Facade — one dependency, one method call.
 * The entire pipeline (Scanner → Parser → NodeBuilder → CodeGenerator
 * → BytecodeStream) is hidden behind Compiler.compile().
 */
public class ClientWithFacade {

    // ── One dependency ──
    private final Compiler compiler;

    public ClientWithFacade(Compiler compiler) {
        this.compiler = compiler;
    }

    public void build(String source) {
        System.out.println("[CLIENT] Compiling: " + source);
        String bytecode = compiler.compile(source);
        System.out.println("[CLIENT] Done.\n" + bytecode);
    }

    public static void main(String[] args) {
        Compiler compiler = new Compiler();
        ClientWithFacade client = new ClientWithFacade(compiler);
        client.build("int x = 42;");
    }
}
