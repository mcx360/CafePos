package com.cafePos.domain.pricing;

import com.cafePos.domain.common.Money;

public final class LoyaltyPercentDiscount implements DiscountPolicy {
    private final int percent;
    public LoyaltyPercentDiscount(int percent) {
        if (percent < 0) throw new IllegalArgumentException(); this.percent = percent;
    }

    @Override public Money discountOf(Money subtotal) {
        var d = subtotal.getAmount().multiply(java.math.BigDecimal.valueOf(percent)).divide(java.math.BigDecimal.valueOf(100));
        return Money.of(d.doubleValue());
    }}