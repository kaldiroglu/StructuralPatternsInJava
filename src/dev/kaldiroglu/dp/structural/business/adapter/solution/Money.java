package dev.kaldiroglu.dp.structural.business.adapter.solution;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public record Money(BigDecimal amount, String currency) {

    private static final Map<String, Integer> MINOR_UNITS = Map.of(
        "USD", 2, "EUR", 2, "GBP", 2, "TRY", 2,
        "JPY", 0, "KRW", 0,
        "BHD", 3, "KWD", 3
    );

    public Money {
        if (amount == null || currency == null) throw new IllegalArgumentException("nulls");
        if (amount.signum() < 0) throw new IllegalArgumentException("negative amount");
    }

    public long toMinorUnits() {
        int scale = MINOR_UNITS.getOrDefault(currency, 2);
        return amount.movePointRight(scale).setScale(0, RoundingMode.HALF_UP).longValueExact();
    }
}
