# Proxy — Caching + Authorization Stack

## Scenario

`CustomerProfileService` is a slow, read-heavy service backed by a remote system. Two cross-cutting concerns sit on top of it:

- **Authorization**: callers can only read profiles they're entitled to. A support agent can read any customer in their region; a regular user can only read their own.
- **Caching**: a profile rarely changes — repeated reads within a short TTL should not hit the backend.

We don't want to bake either concern into the service itself, and we don't want every consumer to remember to wrap calls in security checks.

## Without the pattern

Either:

- The service grows an `auth` parameter and an internal cache, conflating responsibilities.
- Every caller writes its own `if (canRead(currentUser, customerId)) ...` and its own caching helper. Different call sites reach different conclusions about who can see what. The backend is hammered.

See `problem/CustomerProfileService.java` and `problem/SupportConsole.java`.

## With the Proxy pattern

`CustomerProfileService` is an interface implemented by:

- `RemoteCustomerProfileService` — the real, slow backend.
- `AuthorizationProxy` — a **protection proxy** that consults an `AccessPolicy` before forwarding.
- `CachingProxy` — a **caching proxy** that memoizes successful reads with a TTL.

Stack order is meaningful:

```java
CustomerProfileService service =
    new AuthorizationProxy(
        new CachingProxy(
            new RemoteCustomerProfileService(),
            Duration.ofMinutes(5)),
        accessPolicy);
```

Authorization is **outside** the cache — otherwise an unauthorized request would still warm the cache and could be served on a later request from someone who shouldn't see it. The cache only ever holds responses that were authorized for *somebody*; the proxy still re-checks for each caller.

Each proxy is independently testable, and the consumer code is unchanged from the day it called the bare service. New concerns (audit logging, metrics, circuit breaker) become more proxies in the stack.

The trap experienced developers must avoid: **proxies must remain transparent to the interface they implement**. A caching proxy that exposes `invalidate()` outside the interface either invites callers to depend on the proxy class directly (defeating the point) or has to live behind the interface as an explicit cache-control method — at which point you're back to deciding it's not really transparent. Pick the model deliberately, not by accident.

See `solution/`.
