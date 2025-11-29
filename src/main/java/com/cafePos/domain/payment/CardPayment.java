package com.cafePos.domain.payment;

import com.cafePos.domain.Order;

public final class CardPayment implements PaymentStrategy {
    private final String cardNumber;
    public CardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }


    @Override
    public void pay(Order order) {
        int last4Digits = cardNumber.length()-4;
        StringBuilder sb = new StringBuilder();
        for (int i = 0;i<(cardNumber.length()-4);i++) {
            sb.append("*");
        }
        System.out.println("[Card] Customer paid " +
                order.totalWithTax(10) + " EUR"+" with card "+sb+cardNumber.substring(last4Digits));
    }
}