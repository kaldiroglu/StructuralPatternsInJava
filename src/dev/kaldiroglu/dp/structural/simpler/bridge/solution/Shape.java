package dev.kaldiroglu.dp.structural.simpler.bridge.solution;

public abstract class Shape {

    protected final Color color;

    protected Shape(Color color) {
        this.color = color;
    }

    public abstract void draw();
}
