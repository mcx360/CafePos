package com.cafePos.decorator;
import com.cafePos.common.Money;
import com.cafePos.common.Product;
import com.cafePos.decorator.ProductDecorator;
import com.cafePos.common.Priced;
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