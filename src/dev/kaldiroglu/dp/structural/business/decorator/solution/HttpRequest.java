package dev.kaldiroglu.dp.structural.business.decorator.solution;

import java.util.HashMap;
import java.util.Map;

public final class HttpRequest {

    private final String method;
    private final String url;
    private final Map<String, String> headers;

    public HttpRequest(String method, String url) {
        this(method, url, new HashMap<>());
    }

    private HttpRequest(String method, String url, Map<String, String> headers) {
        this.method = method;
        this.url = url;
        this.headers = headers;
    }

    public String method() { return method; }
    public String url() { return url; }
    public Map<String, String> headers() { return headers; }

    public HttpRequest withHeader(String name, String value) {
        Map<String, String> next = new HashMap<>(headers);
        next.put(name, value);
        return new HttpRequest(method, url, next);
    }
}
