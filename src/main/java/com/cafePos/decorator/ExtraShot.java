package com.cafePos.decorator;
import com.cafePos.common.Product;
import com.cafePos.common.Money;
import com.cafePos.common.Priced;
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





