package dev.kaldiroglu.dp.structural.simpler.proxy.solution;

public class ImageProxy implements Image {

    private final String path;
    private RealImage real; // constructed lazily on first display()

    public ImageProxy(String path) {
        this.path = path;
    }

    @Override
    public void display() {
        if (real == null) {
            real = new RealImage(path);
        }
        real.display();
    }
}
