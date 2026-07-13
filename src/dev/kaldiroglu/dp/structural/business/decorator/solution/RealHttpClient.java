package dev.kaldiroglu.dp.structural.business.decorator.solution;

import java.util.concurrent.atomic.AtomicInteger;

/** The one client that actually does I/O. Simulated flakiness for the demo. */
public class RealHttpClient implements HttpClient {

    private final AtomicInteger callCount = new AtomicInteger();

    @Override
    public HttpResponse send(HttpRequest req) {
        int n = callCount.incrementAndGet();
        if (n % 3 == 0 && req.url().endsWith("/users/42")) {
            return new HttpResponse(502, "bad gateway");
        }
        String auth = req.headers().getOrDefault("Authorization", "anon");
        return new HttpResponse(200, "{\"auth\":\"" + auth + "\",\"url\":\"" + req.url() + "\"}");
    }
}
