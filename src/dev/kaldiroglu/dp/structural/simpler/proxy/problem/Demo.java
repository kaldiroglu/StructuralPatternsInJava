package dev.kaldiroglu.dp.structural.simpler.proxy.problem;

public class Demo {
    public static void main(String[] args) {
        // Every image loaded from disk eagerly, even though we display only one.
        RealImage[] gallery = {
            new RealImage("photo1.jpg"),
            new RealImage("photo2.jpg"),
            new RealImage("photo3.jpg"),
            new RealImage("photo4.jpg"),
            new RealImage("photo5.jpg")
        };

        gallery[2].display();
    }
}
