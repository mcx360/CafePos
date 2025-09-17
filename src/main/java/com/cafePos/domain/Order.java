package com.cafePos.domain;

import java.util.ArrayList;
import java.util.List;

public final class Order {
    private final long id;
    private final List<LineItem> items = new ArrayList<>();
    public Order(long id) { this.id = id; }
    public void addItem(LineItem li) {
        if(li.quantity()>0){
            items.add(li);
        }
    }
    public Money subtotal() {
        Money subtotal;
      for(int i =0;i<items.size();i++){
         LineItem lineItem =  items.get(i);

         subtotal += lineItem.lineTotal();

        }
        return subtotal;
                items.stream().map(LineItem::lineTotal).reduce(Money.zero()
                        , Money::add);
    }
    public Money taxAtPercent(int percent) { }
    public Money totalWithTax(int percent) { }
}
