package dev.kaldiroglu.dp.structural.facade.gof;

/**
 * BEFORE the Facade — every client carries 4 subsystem dependencies
 * and orchestrates the pipeline manually.
 *
 * This matches the GoF book: the client directly depends on Scanner,
 * Parser, ProgramNodeBuilder, and CodeGenerator (abstract).
 * BytecodeStream is hidden inside CodeGenerator.
 */
public class ClientBeforeFacade {

    // ── Four dependencies (matching GoF) ──
    private final Scanner             scanner;
    private final Parser              parser;
    private final ProgramNodeBuilder  nodeBuilder;
    private final CodeGenerator       codeGenerator;

    public ClientBeforeFacade() {
        this.scanner       = new Scanner();
        this.nodeBuilder   = new ProgramNodeBuilder();
        this.parser        = new Parser(scanner);
        // Client depends on abstract CodeGenerator, not RISCCodeGenerator
        this.codeGenerator = new RISCCodeGenerator();
    }

    /**
     * Raw compile: the client must know the pipeline order, which
     * classes to instantiate, and how to wire them together.
     * Every client that compiles duplicates these 6-7 lines.
     */
    public void compile(String source) {
        // Step 1+2: lex + parse
        parser.parse(source, nodeBuilder);

        // Step 3: get AST root
        ProgramNode root = nodeBuilder.getRootNode();
        if (root == null) {
            System.out.println("[ERROR] Empty program.");
            return;
        }

        // Step 4: codegen → BytecodeStream (hidden from client)
        System.out.println("[CLIENT] Starting code generation...");
        root.traverse(codeGenerator);

        // Step 5: collect output from CodeGenerator (which owns the stream)
        String output = codeGenerator.getBytecode();
        System.out.println("[CLIENT] Compilation done.\n" + output);
    }

    public static void main(String[] args) {
        new ClientBeforeFacade().compile("int x = 42;");
    }
}
