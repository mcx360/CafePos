package com.cafePos.domain.pricing;

import com.cafePos.domain.common.Money;

public final class FixedRateTaxPolicy implements TaxPolicy {
    private final int percent;
    public FixedRateTaxPolicy(int percent) { if (percent < 0) throw new
            IllegalArgumentException(); this.percent = percent; }
    @Override public Money taxOn(Money amount) {
        var t = amount.getAmount().multiply(java.math.BigDecimal.valueOf(percent)).divide(java.math.BigDecimal.valueOf(100));
        return Money.of(t.doubleValue());
    }
}

