package com.cafePos.app.events;

public record OrderPaid(long orderId) implements OrderEvent { }
