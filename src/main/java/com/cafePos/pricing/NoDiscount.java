package com.cafePos.pricing;

import com.cafePos.common.Money;

public final class NoDiscount implements DiscountPolicy {
    @Override public Money discountOf(Money subtotal) {
        return Money.zero(); }
}