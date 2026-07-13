# Bridge — Problem

We have shapes (`Circle`, `Square`) that need to be drawn in different colors (`Red`, `Blue`). Each combination must produce different output.

## Without the pattern

The natural reflex is one subclass per combination: `RedCircle`, `BlueCircle`, `RedSquare`, `BlueSquare`. With *M* shapes and *N* colors you get *M × N* classes — adding one new color forces *M* new classes, adding one new shape forces *N*. The two dimensions of variation are tangled into a single inheritance tree.

See `problem/`.

## With the Bridge pattern

We split the hierarchy in two:

- **Abstraction**: `Shape` — holds a reference to a `Color`. Subclasses: `Circle`, `Square`.
- **Implementor**: `Color` — interface with `applyColor()`. Implementations: `RedColor`, `BlueColor`.

`Shape` and `Color` evolve independently. Adding a new color is one new class. Adding a new shape is one new class. *M + N* instead of *M × N*.

See `solution/`.
