package dev.kaldiroglu.dp.structural.simpler.adapter.solution;

public class Demo {
    public static void main(String[] args) {
        MediaPlayer player = new AudioPlayer();
        player.play("mp3", "song.mp3");
        player.play("mp4", "movie.mp4");
        player.play("vlc", "clip.vlc");
        player.play("avi", "old.avi");
    }
}
