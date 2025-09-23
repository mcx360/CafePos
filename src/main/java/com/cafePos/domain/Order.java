package com.cafePos.domain;

import com.cafePos.common.Money;

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
    }
    public Money taxAtPercent(int percent) {

       return  subtotal().multiply((percent/100));

    }
    public Money totalWithTax(int percent) {
        return subtotal ().add(taxAtPercent(percent));

    }
}
