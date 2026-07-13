# Adapter — Problem

We have an `AudioPlayer` that plays MP3 natively. We need it to also play VLC and MP4 files, but the libraries that handle those formats come from third parties and expose **different interfaces** (`playVlc`, `playMp4`) than the one our client expects (`play`).

## Without the pattern

`AudioPlayer.play()` becomes a growing `if/else` chain that hard-codes every new format and its quirky API. Every time a new format arrives, we crack open `AudioPlayer` again, violating the open/closed principle.

See `problem/AudioPlayer.java`.

## With the Adapter pattern

We introduce an `AdvancedMediaPlayer` interface that the third-party players implement, and a `MediaAdapter` that translates calls on the client-facing `MediaPlayer` interface into calls on `AdvancedMediaPlayer`. `AudioPlayer` now talks only to `MediaPlayer` and delegates unknown formats to a `MediaAdapter`. Adding a new format means adding one new adapter — no edits to existing code.

See `solution/`.
