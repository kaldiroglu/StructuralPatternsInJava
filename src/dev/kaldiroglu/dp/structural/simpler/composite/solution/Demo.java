package dev.kaldiroglu.dp.structural.simpler.composite.solution;

public class Demo {
    public static void main(String[] args) {
        FileSystemNode root = new Directory("root")
            .add(new File("readme.txt", 100))
            .add(new Directory("src")
                .add(new File("Main.java", 500))
                .add(new File("Utils.java", 300)));

        // Same call regardless of leaf vs. composite:
        printSize(root);
    }

    private static void printSize(FileSystemNode node) {
        System.out.println(node.getName() + " — " + node.getSize() + " bytes");
    }
}
