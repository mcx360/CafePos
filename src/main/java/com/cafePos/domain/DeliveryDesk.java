package com.cafePos.domain;

public final class DeliveryDesk implements OrderObserver {
    @Override
    public void updated(Order order, String eventType) {
        if(eventType.equalsIgnoreCase("ready")){
            System.out.println("[Delivery] Order #" + order.getID() + " is ready for delivery");
        }
    }
}