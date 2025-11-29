package com.cafePos.domain.payment;

import com.cafePos.domain.Order;

public final class CashPayment implements PaymentStrategy {
    @Override
    public  void pay(Order order) {
        System.out.println("[Cash] Customer paid " + order.totalWithTax(10) + " EUR");
    }

}