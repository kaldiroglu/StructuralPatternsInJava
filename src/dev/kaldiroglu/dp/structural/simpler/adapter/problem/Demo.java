package dev.kaldiroglu.dp.structural.simpler.adapter.problem;

public class Demo {
    public static void main(String[] args) {
        AudioPlayer player = new AudioPlayer();
        player.play("mp3", "song.mp3");
        player.play("mp4", "movie.mp4");
        player.play("vlc", "clip.vlc");
        player.play("avi", "old.avi");
    }
}
