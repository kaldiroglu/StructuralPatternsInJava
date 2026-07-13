package dev.kaldiroglu.dp.structural.business.decorator.solution;

import java.util.function.Supplier;

public class AuthDecorator extends HttpClientDecorator {

    private final Supplier<String> tokenSupplier;

    public AuthDecorator(HttpClient delegate, Supplier<String> tokenSupplier) {
        super(delegate);
        this.tokenSupplier = tokenSupplier;
    }

    @Override
    public HttpResponse send(HttpRequest request) {
        return delegate.send(request.withHeader("Authorization", "Bearer " + tokenSupplier.get()));
    }
}
