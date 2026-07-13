package dev.kaldiroglu.dp.structural.business.facade.solution;

import java.util.List;

public record Cart(String customerId, List<CartLine> lines) {
    public record CartLine(String sku, int quantity) {}
}
