package dev.kaldiroglu.dp.structural.simpler.bridge.solution;

public class Demo {
    public static void main(String[] args) {
        Shape[] shapes = {
            new Circle(new RedColor()),
            new Circle(new BlueColor()),
            new Square(new RedColor()),
            new Square(new BlueColor())
            // Adding a green color = one new class. Adding a triangle = one new class.
        };
        for (Shape s : shapes) s.draw();
    }
}
