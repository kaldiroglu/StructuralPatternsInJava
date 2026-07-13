package dev.kaldiroglu.dp.structural.simpler.composite.solution;

import java.util.ArrayList;
import java.util.List;

public class Directory implements FileSystemNode {

    private final String name;
    private final List<FileSystemNode> children = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    public Directory add(FileSystemNode child) {
        children.add(child);
        return this;
    }

    @Override
    public String getName() { return name; }

    @Override
    public long getSize() {
        long total = 0;
        for (FileSystemNode child : children) {
            total += child.getSize();
        }
        return total;
    }
}
