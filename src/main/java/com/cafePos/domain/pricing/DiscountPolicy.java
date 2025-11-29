package com.cafePos.domain.pricing;

import com.cafePos.domain.common.Money;

public interface DiscountPolicy {
    Money discountOf(Money subtotal);
}