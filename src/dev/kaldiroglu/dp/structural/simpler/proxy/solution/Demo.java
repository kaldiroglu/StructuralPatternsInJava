package dev.kaldiroglu.dp.structural.simpler.proxy.solution;

public class Demo {
    public static void main(String[] args) {
        // No disk reads happen here — proxies are cheap to construct.
        Image[] gallery = {
            new ImageProxy("photo1.jpg"),
            new ImageProxy("photo2.jpg"),
            new ImageProxy("photo3.jpg"),
            new ImageProxy("photo4.jpg"),
            new ImageProxy("photo5.jpg")
        };

        System.out.println("Gallery built (no disk I/O yet).");

        // Only photo3 actually loads from disk.
        gallery[2].display();
        // Calling it again uses the cached RealImage — still just one disk load.
        gallery[2].display();
    }
}
