package dev.kaldiroglu.dp.structural.business.decorator.solution;

public abstract class HttpClientDecorator implements HttpClient {

    protected final HttpClient delegate;

    protected HttpClientDecorator(HttpClient delegate) {
        this.delegate = delegate;
    }

    @Override
    public HttpResponse send(HttpRequest request) {
        return delegate.send(request);
    }
}
