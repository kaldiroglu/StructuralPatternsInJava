package dev.kaldiroglu.dp.structural.simpler.bridge.problem;

public class Demo {
    public static void main(String[] args) {
        Shape[] shapes = {
            new RedCircle(),
            new BlueCircle(),
            new RedSquare(),
            new BlueSquare()
            // Adding green requires GreenCircle and GreenSquare too.
        };
        for (Shape s : shapes) s.draw();
    }
}
