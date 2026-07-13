package dev.kaldiroglu.dp.structural.simpler.adapter.solution;

public class AudioPlayer implements MediaPlayer {

    @Override
    public void play(String type, String fileName) {
        if (type.equalsIgnoreCase("mp3")) {
            System.out.println("Playing mp3 file: " + fileName);
        } else if (type.equalsIgnoreCase("vlc") || type.equalsIgnoreCase("mp4")) {
            new MediaAdapter(type).play(type, fileName);
        } else {
            System.out.println("Format not supported: " + type);
        }
    }
}
