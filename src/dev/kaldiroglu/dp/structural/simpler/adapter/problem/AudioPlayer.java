package dev.kaldiroglu.dp.structural.simpler.adapter.problem;

public class AudioPlayer {

    public void play(String type, String fileName) {
        if (type.equalsIgnoreCase("mp3")) {
            System.out.println("Playing mp3 file: " + fileName);
        } else if (type.equalsIgnoreCase("mp4")) {
            System.out.println("Playing mp4 file (hard-coded branch): " + fileName);
        } else if (type.equalsIgnoreCase("vlc")) {
            System.out.println("Playing vlc file (hard-coded branch): " + fileName);
        } else {
            System.out.println("Format not supported: " + type);
        }
    }
}
