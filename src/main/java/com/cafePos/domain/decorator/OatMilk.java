package com.cafePos.domain.decorator;

import com.cafePos.domain.common.Money;
import com.cafePos.domain.common.Product;
import com.cafePos.domain.common.Priced;

public final class OatMilk extends ProductDecorator {
    private static final Money SURCHARGE = Money.of(0.50);
    public OatMilk(Product base) {
        super(base);
    }
    @Override
    public String name() { return base.name() + " + Oat Milk"; }
    public Money price() {
        Money unit = (base instanceof Priced) ?  ((Priced) base).price() : base.basePrice();
        return unit.add(SURCHARGE);
    }
}