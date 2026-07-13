package dev.kaldiroglu.dp.structural.business.facade.problem;

import java.util.List;

public record Cart(String customerId, List<CartLine> lines) {
    public record CartLine(String sku, int quantity) {}
}
