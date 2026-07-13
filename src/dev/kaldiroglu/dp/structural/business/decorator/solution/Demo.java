package dev.kaldiroglu.dp.structural.business.decorator.solution;

import java.time.Duration;

public class Demo {
    public static void main(String[] args) {
        // Internal-service profile: cache outside retry, retry outside auth, log outermost.
        // Auth must run before caching so different tokens don't share entries.
        HttpClient internal =
            new LoggingDecorator(
                new CachingDecorator(
                    new RetryDecorator(
                        new AuthDecorator(new RealHttpClient(), () -> "token-xyz"),
                        3),
                    Duration.ofSeconds(30)));

        // Health probe: nothing fancy.
        HttpClient probe = new RealHttpClient();

        System.out.println(internal.send(new HttpRequest("GET", "/users/42")));
        System.out.println(internal.send(new HttpRequest("GET", "/users/42"))); // cache hit
        System.out.println(probe.send(new HttpRequest("GET", "/healthz")));
    }
}
