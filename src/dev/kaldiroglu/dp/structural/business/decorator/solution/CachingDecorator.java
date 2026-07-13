package dev.kaldiroglu.dp.structural.business.decorator.solution;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/** Caches successful GETs for a fixed TTL. Cache key includes Authorization header
 *  so two callers with different tokens never share entries. */
public class CachingDecorator extends HttpClientDecorator {

    private record Entry(HttpResponse response, Instant expires) {}

    private final Map<String, Entry> cache = new HashMap<>();
    private final Duration ttl;

    public CachingDecorator(HttpClient delegate, Duration ttl) {
        super(delegate);
        this.ttl = ttl;
    }

    @Override
    public HttpResponse send(HttpRequest request) {
        if (!"GET".equalsIgnoreCase(request.method())) return delegate.send(request);

        String key = cacheKey(request);
        Entry e = cache.get(key);
        Instant now = Instant.now();
        if (e != null && now.isBefore(e.expires())) return e.response();

        HttpResponse response = delegate.send(request);
        if (response.status() >= 200 && response.status() < 300) {
            cache.put(key, new Entry(response, now.plus(ttl)));
        }
        return response;
    }

    private static String cacheKey(HttpRequest r) {
        return r.url() + "|" + r.headers().getOrDefault("Authorization", "");
    }
}
