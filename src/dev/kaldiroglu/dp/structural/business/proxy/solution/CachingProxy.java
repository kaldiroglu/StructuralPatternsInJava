package dev.kaldiroglu.dp.structural.business.proxy.solution;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Caching proxy. Keyed only on customerId (not Caller) — the cached profile
 * itself is caller-agnostic; access decisions happen in the AuthorizationProxy
 * upstream. TTL-based eviction.
 */
public class CachingProxy implements CustomerProfileService {

    private record Entry(CustomerProfile value, Instant expires) {}

    private final CustomerProfileService delegate;
    private final Duration ttl;
    private final ConcurrentHashMap<String, Entry> cache = new ConcurrentHashMap<>();

    public CachingProxy(CustomerProfileService delegate, Duration ttl) {
        this.delegate = delegate;
        this.ttl = ttl;
    }

    @Override
    public CustomerProfile getProfile(Caller caller, String customerId) {
        Instant now = Instant.now();
        Entry e = cache.get(customerId);
        if (e != null && now.isBefore(e.expires())) return e.value();

        CustomerProfile fresh = delegate.getProfile(caller, customerId);
        cache.put(customerId, new Entry(fresh, now.plus(ttl)));
        return fresh;
    }
}
