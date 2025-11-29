package com.cafePos.domain.decorator;

import com.cafePos.domain.common.Product;
import com.cafePos.domain.common.Money;
import com.cafePos.domain.common.Priced;

public final class ExtraShot extends ProductDecorator {
    private static final Money SURCHARGE = Money.of(0.80);
    public ExtraShot(Product base) { super(base); }
    @Override public String name() {
        return base.name() + " + Extra Shot"; }
        public Money price() {
            Money unit = (base instanceof Priced) ?  ((Priced) base).price() : base.basePrice();
            return unit.add(SURCHARGE);
    }
    }





