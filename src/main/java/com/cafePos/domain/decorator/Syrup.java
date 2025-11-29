package com.cafePos.domain.decorator;

import com.cafePos.domain.common.Priced;
import com.cafePos.domain.common.Money;
import com.cafePos.domain.common.Product;

public final class Syrup extends ProductDecorator {
    private static final Money SURCHARGE = Money.of(0.40);
    public Syrup(Product base) { super(base); }
    @Override public String name() { return base.name() + " + Syrup"; }
    public Money price() {
        Money unit = (base instanceof Priced) ?  ((Priced) base).price() : base.basePrice();
        return unit.add(SURCHARGE);
    }
}