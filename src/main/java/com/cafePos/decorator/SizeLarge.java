package com.cafePos.decorator;
import com.cafePos.common.Priced;
import com.cafePos.common.Money;
import com.cafePos.common.Product;

public final class SizeLarge extends ProductDecorator {
    private static final Money SURCHARGE = Money.of(0.70);
    public SizeLarge(Product base) { super(base); }
    @Override public String name() { return base.name() + " (Large)"; }
    public Money price() {
        Money unit = (base instanceof Priced) ?  ((Priced) base).price() : base.basePrice();
        return unit.add(SURCHARGE);
    }
}

