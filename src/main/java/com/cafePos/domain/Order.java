package com.cafePos.domain;

import com.cafePos.common.Money;
import com.cafePos.payment.PaymentStrategy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Order {
    public final long id;
    private final List<LineItem> items = new ArrayList<>();
    private final List<OrderObserver> observers = new ArrayList<>();


    public Order(long id) { this.id = id; }

    public void addItem(LineItem li) {
        if(li.quantity()>0){
            items.add(li);
        }
        notifyObservers("Item added!");
    }
    public Money subtotal() {
        return items.stream().map(LineItem::lineTotal).reduce(Money.zero()
                , Money::add);
    }
    public Money taxAtPercent(int percent) {

       return  subtotal().multiply(percent/100.0);

    }
    public Money totalWithTax(int percent) {
        return subtotal ().add(taxAtPercent(percent));

    }

    public long getID(){
        return id;
    }

    public List<LineItem> getItems(){
        return items;
    }

    public void pay(PaymentStrategy strategy){
        if (strategy == null) throw new IllegalArgumentException("strategy required");
        strategy.pay(this);
        notifyObservers("Paid!");
    }

    public void register(OrderObserver o) {
// TODO: add null check and add the observer
        if(Objects.nonNull(o)){
            if(!observers.contains(o)) {
                observers.add(o);
            }

        }

   }
  public void unregister(OrderObserver o) {
// TODO: remove the observer if present
      observers.remove(o);
    }


    private void notifyObservers(String eventType) {
// TODO: iterate observers and call updated(this,eventType)
       for(OrderObserver o :observers){

           o.updated(this,eventType);
       }

   }

    public void markReady() {
//// TODO: just publish notifyObservers("ready")
        notifyObservers("ready");
       }

}
