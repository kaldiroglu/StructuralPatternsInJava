# Decorator — HTTP Client Pipeline

## Scenario

Every team in a microservice estate needs an HTTP client, but the cross-cutting concerns vary per call site:

- An internal service call needs **auth** + **retry on 5xx** + **structured logging**.
- A third-party `GET` for a slowly-changing resource needs **caching** + **logging**.
- A health probe wants **none of it**.

We don't want to fork the HTTP client for each combination, and we don't want a config-driven "kitchen-sink" client where every concern is wired in conditionally.

## Without the pattern

A single `HttpClient` accumulates flags: `client.withRetry(true).withCache(true).withAuth(token)`. The internals become an `if (retryEnabled) { ... }` ladder. The order in which concerns apply is hard-coded and hard to vary per caller. Tests for one concern become tests for the whole client.

See `problem/HttpClient.java`.

## With the Decorator pattern

`HttpClient` is an interface with `send(HttpRequest) -> HttpResponse`. We have:

- One concrete client that actually does I/O: `RealHttpClient`.
- Decorators that each wrap an `HttpClient` and add **one** concern: `AuthDecorator`, `RetryDecorator`, `LoggingDecorator`, `CachingDecorator`.

Composition expresses both *which* concerns apply and *in what order*:

```java
HttpClient client =
    new LoggingDecorator(
      new RetryDecorator(
        new AuthDecorator(
          new RealHttpClient(),
          () -> currentToken()),
        3));
```

**Order matters** — and that's the lesson:

- `Cache(Retry(Real))` returns cached responses without retrying. A different choice from `Retry(Cache(Real))`, which retries the cache lookup itself.
- Logging on the outside logs the post-retry behavior; logging on the inside logs every attempt.
- Auth must be applied *before* caching (a cache key shouldn't depend on a token), and *before* retry (otherwise an expired token gets retried as-is).

Each decorator is independently testable, swappable per call site, and one new concern is one new class.

See `solution/`.
