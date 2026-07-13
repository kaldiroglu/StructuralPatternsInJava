package dev.kaldiroglu.dp.structural.business.composite.problem;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private final String name;
    private final List<Object> children = new ArrayList<>(); // Employee or Department

    public Department(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public List<Object> getChildren() { return children; }

    public void add(Object child) {
        if (!(child instanceof Employee) && !(child instanceof Department))
            throw new IllegalArgumentException("Unknown child type");
        children.add(child);
    }
}
