package dev.kaldiroglu.dp.structural.simpler.bridge.solution;

public class Square extends Shape {

    public Square(Color color) {
        super(color);
    }

    @Override
    public void draw() {
        System.out.println("Drawing a " + color.applyColor() + " Square");
    }
}
