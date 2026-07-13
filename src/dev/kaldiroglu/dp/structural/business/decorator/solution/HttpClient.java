package dev.kaldiroglu.dp.structural.business.decorator.solution;

public interface HttpClient {
    HttpResponse send(HttpRequest request);
}
