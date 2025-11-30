package com.cafePos.app.events;

public record OrderCreated(long orderId) implements OrderEvent { }
