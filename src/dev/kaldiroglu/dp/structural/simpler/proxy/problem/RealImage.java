package dev.kaldiroglu.dp.structural.simpler.proxy.problem;

public class RealImage {

    private final String path;

    public RealImage(String path) {
        this.path = path;
        loadFromDisk(); // expensive — happens on every construction
    }

    private void loadFromDisk() {
        System.out.println("Loading from disk: " + path);
    }

    public void display() {
        System.out.println("Displaying: " + path);
    }
}
