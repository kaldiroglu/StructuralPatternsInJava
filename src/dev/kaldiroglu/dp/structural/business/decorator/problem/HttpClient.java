package dev.kaldiroglu.dp.structural.business.decorator.problem;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * One client, ever-growing knobs. Order of concerns is hard-coded; you cannot
 * pick "no-cache, three retries" at one call site and "cache, no retry" at another
 * without flag gymnastics.
 */
public class HttpClient {

    private final AtomicInteger callCount = new AtomicInteger();
    private boolean loggingEnabled = false;
    private boolean retryEnabled = false;
    private int maxRetries = 0;
    private boolean cachingEnabled = false;
    private final Map<String, String> cache = new HashMap<>();
    private String authToken = null;

    public HttpClient withLogging(boolean v)       { this.loggingEnabled = v; return this; }
    public HttpClient withRetry(boolean v, int n)  { this.retryEnabled = v; this.maxRetries = n; return this; }
    public HttpClient withCache(boolean v)         { this.cachingEnabled = v; return this; }
    public HttpClient withAuth(String token)       { this.authToken = token; return this; }

    public String send(String method, String url) {
        if (cachingEnabled && method.equals("GET") && cache.containsKey(url)) {
            if (loggingEnabled) System.out.println("LOG cache-hit " + url);
            return cache.get(url);
        }

        int attempts = retryEnabled ? maxRetries + 1 : 1;
        RuntimeException last = null;
        for (int i = 0; i < attempts; i++) {
            try {
                if (loggingEnabled) System.out.println("LOG try " + (i + 1) + "/" + attempts + " " + method + " " + url);
                String response = doSend(method, url);
                if (cachingEnabled && method.equals("GET")) cache.put(url, response);
                return response;
            } catch (RuntimeException e) {
                last = e;
            }
        }
        throw last;
    }

    private String doSend(String method, String url) {
        int n = callCount.incrementAndGet();
        // simulate flakiness: every 3rd call fails the first time
        if (n % 3 == 0 && url.endsWith("/users/42")) throw new RuntimeException("502 bad gateway");
        return "{\"auth\":\"" + (authToken == null ? "anon" : authToken) + "\",\"url\":\"" + url + "\"}";
    }
}
