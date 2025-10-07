package com.cafePos.domain;

public class CustomerNotifier implements OrderObserver{
    @Override
    public void updated(Order order, String eventType) {
        System.out.println("[Customer] Dear customer, your Order " + order.getID() + " has been updated: "+ eventType);
    }

}
