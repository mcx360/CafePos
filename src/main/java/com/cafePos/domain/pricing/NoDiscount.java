package com.cafePos.domain.pricing;

import com.cafePos.domain.common.Money;

public final class NoDiscount implements DiscountPolicy {
    @Override public Money discountOf(Money subtotal) {
        return Money.zero(); }
}