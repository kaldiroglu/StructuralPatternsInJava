package dev.kaldiroglu.dp.structural.business.decorator.solution;

public class RetryDecorator extends HttpClientDecorator {

    private final int maxAttempts;

    public RetryDecorator(HttpClient delegate, int maxAttempts) {
        super(delegate);
        if (maxAttempts < 1) throw new IllegalArgumentException("maxAttempts >= 1");
        this.maxAttempts = maxAttempts;
    }

    @Override
    public HttpResponse send(HttpRequest request) {
        HttpResponse last = null;
        for (int i = 0; i < maxAttempts; i++) {
            last = delegate.send(request);
            if (last.status() < 500) return last;
        }
        return last;
    }
}
