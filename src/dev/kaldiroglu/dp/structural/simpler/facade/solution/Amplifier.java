package dev.kaldiroglu.dp.structural.simpler.facade.solution;

public class Amplifier {
    public void on() { System.out.println("Amplifier on"); }
    public void off() { System.out.println("Amplifier off"); }
    public void setSurroundSound() { System.out.println("Amplifier surround mode"); }
    public void setVolume(int level) { System.out.println("Amplifier volume: " + level); }
}
