package dev.kaldiroglu.dp.structural.simpler.composite.problem;

import java.util.ArrayList;
import java.util.List;

public class Directory {
    private final String name;
    private final List<Object> children = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    public void add(Object child) {
        if (!(child instanceof File) && !(child instanceof Directory)) {
            throw new IllegalArgumentException("Unknown child type: " + child);
        }
        children.add(child);
    }

    public String getName() { return name; }

    public long getSize() {
        long total = 0;
        for (Object child : children) {
            if (child instanceof File f) {
                total += f.getSize();
            } else if (child instanceof Directory d) {
                total += d.getSize();
            }
        }
        return total;
    }
}
