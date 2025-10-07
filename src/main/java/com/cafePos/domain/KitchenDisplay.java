package com.cafePos.domain;

public final class KitchenDisplay implements OrderObserver {
    @Override
    public void updated(Order order, String eventType) {
// TODO: on "itemAdded" -> print "[Kitchen] Order#<id>: item added"
// on "paid" -> print "[Kitchen] Order#<id>: payment received"
        if(eventType.equalsIgnoreCase("Item added")){
            System.out.println("[Kitchen] Order# "+order.getID()+": item added");
        }else if(eventType.equalsIgnoreCase("Paid")){
            System.out.println("[Kitchen] Order# "+order.getID()+": payment received");
        }
    }
}

