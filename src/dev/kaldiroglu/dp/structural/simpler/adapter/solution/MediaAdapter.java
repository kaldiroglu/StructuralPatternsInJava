package dev.kaldiroglu.dp.structural.simpler.adapter.solution;

public class MediaAdapter implements MediaPlayer {

    private final AdvancedMediaPlayer advancedPlayer;

    public MediaAdapter(String type) {
        if (type.equalsIgnoreCase("vlc")) {
            this.advancedPlayer = new VlcPlayer();
        } else if (type.equalsIgnoreCase("mp4")) {
            this.advancedPlayer = new Mp4Player();
        } else {
            throw new IllegalArgumentException("Unsupported advanced format: " + type);
        }
    }

    @Override
    public void play(String type, String fileName) {
        if (type.equalsIgnoreCase("vlc")) {
            advancedPlayer.playVlc(fileName);
        } else if (type.equalsIgnoreCase("mp4")) {
            advancedPlayer.playMp4(fileName);
        }
    }
}
