package dev.kaldiroglu.dp.structural.business.decorator.solution;

public class LoggingDecorator extends HttpClientDecorator {

    public LoggingDecorator(HttpClient delegate) {
        super(delegate);
    }

    @Override
    public HttpResponse send(HttpRequest request) {
        long start = System.nanoTime();
        HttpResponse response = delegate.send(request);
        long elapsedMicros = (System.nanoTime() - start) / 1_000;
        System.out.println("LOG " + request.method() + " " + request.url()
            + " -> " + response.status() + " (" + elapsedMicros + "us)");
        return response;
    }
}
