package dev.kaldiroglu.dp.structural.simpler.proxy.solution;

public class RealImage implements Image {

    private final String path;

    public RealImage(String path) {
        this.path = path;
        loadFromDisk();
    }

    private void loadFromDisk() {
        System.out.println("Loading from disk: " + path);
    }

    @Override
    public void display() {
        System.out.println("Displaying: " + path);
    }
}
