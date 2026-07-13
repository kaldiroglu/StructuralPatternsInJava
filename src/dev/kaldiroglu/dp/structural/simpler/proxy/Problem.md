# Proxy — Problem

We're building a document viewer that can contain many high-resolution images. Loading an image from disk is expensive, but the user might never scroll to most of them.

## Without the pattern

`RealImage` loads its bytes from disk in its constructor. Constructing a hundred `RealImage` objects up front does a hundred disk reads, even if the user only ever displays one. The startup cost is paid for content the user might never see.

See `problem/`.

## With the Proxy pattern

We introduce an `Image` interface implemented by both `RealImage` and `ImageProxy`. The proxy stores the file path but defers constructing the `RealImage` until `display()` is actually called. The client code is unchanged — it still talks to `Image` — but the heavy work is now lazy.

The same shape supports other variants: a remote proxy (calls cross a network), a protection proxy (checks permissions before forwarding), or a caching proxy (memoizes results).

See `solution/`.
