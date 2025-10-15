package com.cafePos.pricing;
import com.cafePos.common.Money;
public interface DiscountPolicy {
    Money discountOf(Money subtotal);
}