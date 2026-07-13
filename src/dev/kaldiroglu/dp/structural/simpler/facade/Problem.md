# Facade — Problem

A home theater has many subsystems: amplifier, DVD player, projector, screen, and lights. To "watch a movie," the client has to: dim the lights, lower the screen, turn on the projector and amplifier, set them up correctly, then start the DVD. To stop, it has to do the reverse.

## Without the pattern

The client knows every subsystem and the correct sequence of calls. Twenty lines of orchestration, repeated everywhere a movie is started or stopped. Any change to a subsystem (new method signature, new step) requires touching every caller.

See `problem/Demo.java` — the client juggles five subsystems directly.

## With the Facade pattern

A `HomeTheaterFacade` exposes two methods, `watchMovie(...)` and `endMovie()`, encapsulating the orchestration. The client talks only to the facade. Subsystems are still available for advanced use, but the common case is one call.

See `solution/`.
