package com.cafePos.domain;

import com.cafePos.common.Money;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public final class Order {
    public final long id;
    private final List<LineItem> items = new ArrayList<>();
    public Order(long id) { this.id = id; }
    public void addItem(LineItem li) {
        if(li.quantity()>0){
            items.add(li);
        }
    }
    public Money subtotal() {
        return items.stream().map(LineItem::lineTotal).reduce(Money.zero()
                , Money::add);
    }
    public Money taxAtPercent(int percent) {
        if (percent < 0) {
            throw new IllegalArgumentException("Tax percent cannot be negative");
        }
        return subtotal().multiply(percent);
    }

    public Money totalWithTax(int percent) {
        //no contract for tax since I assume it is theoretically possible to tax at any rate
        return subtotal().add(taxAtPercent(percent));
    }

    public long getID(){
        return id;
    }

    public List<LineItem> getItems(){
        return items;
    }

}
