package dev.kaldiroglu.dp.structural.simpler.facade.solution;

public class DvdPlayer {
    public void on() { System.out.println("DVD player on"); }
    public void off() { System.out.println("DVD player off"); }
    public void play(String movie) { System.out.println("DVD playing: " + movie); }
    public void stop() { System.out.println("DVD stopped"); }
}
