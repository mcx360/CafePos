package com.cafePos.domain;

public interface OrderObserver {
    void updated(Order order, String eventType);
}
