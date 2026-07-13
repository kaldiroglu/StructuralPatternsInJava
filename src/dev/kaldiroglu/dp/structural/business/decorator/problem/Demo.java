package dev.kaldiroglu.dp.structural.business.decorator.problem;

public class Demo {
    public static void main(String[] args) {
        HttpClient client = new HttpClient()
            .withLogging(true)
            .withRetry(true, 2)
            .withCache(true)
            .withAuth("token-xyz");

        System.out.println(client.send("GET", "/users/42"));
        System.out.println(client.send("GET", "/users/42")); // cached
    }
}
