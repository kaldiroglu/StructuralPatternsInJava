package dev.kaldiroglu.dp.structural.simpler.composite.problem;

public class Demo {
    public static void main(String[] args) {
        Directory root = new Directory("root");
        root.add(new File("readme.txt", 100));

        Directory src = new Directory("src");
        src.add(new File("Main.java", 500));
        src.add(new File("Utils.java", 300));

        root.add(src);

        // Client must know which method is which:
        System.out.println("Root total size: " + root.getSize());
    }
}
