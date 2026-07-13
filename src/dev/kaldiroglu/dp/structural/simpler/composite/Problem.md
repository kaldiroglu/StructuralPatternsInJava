# Composite — Problem

We're modeling a file system with `File` (a leaf, has size) and `Directory` (can contain other files and directories). The client wants to ask for the total size of any node — file or directory — without caring which one it is.

## Without the pattern

`File` and `Directory` are unrelated classes with different APIs. Any code that walks the tree has to use `instanceof` checks to decide whether to call `file.getSize()` or recurse into `directory.getSize()`. The recursion logic leaks out into every caller.

See `problem/`.

## With the Composite pattern

Both `File` and `Directory` implement a common `FileSystemNode` interface with `getSize()` and `getName()`. `Directory.getSize()` recurses over its children — also `FileSystemNode`s — so leaves and composites are treated uniformly. Clients call `node.getSize()` without ever knowing whether they're holding a leaf or a tree.

See `solution/`.
