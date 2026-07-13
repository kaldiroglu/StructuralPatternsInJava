package dev.kaldiroglu.dp.structural.simpler.adapter.solution;

public class VlcPlayer implements AdvancedMediaPlayer {

    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file: " + fileName);
    }

    @Override
    public void playMp4(String fileName) {
        // VlcPlayer does not support mp4
    }
}
