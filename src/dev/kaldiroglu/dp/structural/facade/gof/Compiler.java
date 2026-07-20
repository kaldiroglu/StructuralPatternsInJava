package dev.kaldiroglu.dp.structural.facade.gof;

/**
 * The Compiler Facade — a single entry point that hides the entire
 * compilation subsystem.
 *
 * This matches the GoF example: "The Compiler class is a facade that
 * puts all these pieces together."  Clients never see Scanner, Parser,
 * ProgramNodeBuilder, or CodeGenerator.
 */
public class Compiler {

    public String compile(String source) {
        // Step 1: create the pipeline (hidden from client)
        Scanner scanner               = new Scanner();
        ProgramNodeBuilder nodeBuilder = new ProgramNodeBuilder();
        Parser parser                  = new Parser(scanner);
        CodeGenerator codeGenerator    = new RISCCodeGenerator();

        // Step 2: lex + parse
        parser.parse(source, nodeBuilder);

        // Step 3: codegen
        ProgramNode root = nodeBuilder.getRootNode();
        if (root != null) {
            root.traverse(codeGenerator);
        }

        // Step 4: return bytecode (stream is inside CodeGenerator)
        return codeGenerator.getBytecode();
    }
}
