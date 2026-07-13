package dev.kaldiroglu.dp.structural.simpler.facade.solution;

public class Demo {
    public static void main(String[] args) {
        HomeTheaterFacade theater = new HomeTheaterFacade(
            new Amplifier(),
            new DvdPlayer(),
            new Projector(),
            new Screen(),
            new Lights()
        );

        theater.watchMovie("Inception");
        System.out.println("--- movie over ---");
        theater.endMovie();
    }
}
