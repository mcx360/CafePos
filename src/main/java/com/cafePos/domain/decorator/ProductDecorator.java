package com.cafePos.domain.decorator;

import com.cafePos.domain.common.Priced;
import com.cafePos.domain.common.Product;
import com.cafePos.domain.common.Money;

public abstract class ProductDecorator implements Product, Priced {
    protected final Product base;
    protected ProductDecorator(Product base) {
        if (base == null) throw new
                IllegalArgumentException("base product required");
        this.base = base;
    }
    @Override
    public String id() {
        return base.id();
    } //id may remain the base product id
    @Override
    public Money basePrice() {
        return base.basePrice(); }

    // original price (not total)
    @Override
    public Money price() {
        // If the wrapped product is Priced, use its price(), otherwise use basePrice()
        Money unit = (base instanceof Priced) ? ((Priced) base).price() : base.basePrice();
        return unit;
    }
    // Concrete decorators will override name() and provide a finalPrice() helper if desired.
}
