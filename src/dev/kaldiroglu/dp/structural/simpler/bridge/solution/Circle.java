package dev.kaldiroglu.dp.structural.simpler.bridge.solution;

public class Circle extends Shape {

    public Circle(Color color) {
        super(color);
    }

    @Override
    public void draw() {
        System.out.println("Drawing a " + color.applyColor() + " Circle");
    }
}
