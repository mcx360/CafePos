package com.cafePos.app.events;

public sealed interface OrderEvent permits OrderCreated, OrderPaid { }

