package com.cafePos.demo;

import com.cafePos.domain.catalog.Catalog;
import com.cafePos.domain.catalog.InMemoryCatalog;
import com.cafePos.domain.common.Money;
import com.cafePos.domain.common.SimpleProduct;
import com.cafePos.domain.LineItem;
import com.cafePos.domain.Order;
import com.cafePos.domain.payment.CardPayment;
import com.cafePos.domain.payment.CashPayment;

public final class Week3Demo {
    public static void main(String[] args) {
        Catalog catalog = new InMemoryCatalog();
        catalog.add(new SimpleProduct("P-ESP", "Espresso", Money.of(2.50)));
        catalog.add(new SimpleProduct("P-CCK", "Chocolate Cookie", Money.of(3.50)));
// Cash payment
        Order order1 = new Order(1002);
        order1.addItem(new LineItem(catalog.findById("P-ESP").orElseThrow(), 2));
        order1.addItem(new LineItem(catalog.findById("P-CCK").orElseThrow(), 1));
        System.out.println("Order #" + order1.getID() + " Total: " + order1.totalWithTax(10));
        order1.pay(new CashPayment());
// Card payment
        Order order2 = new Order(1003);
        order2.addItem(new LineItem(catalog.findById("P-ESP").orElseThrow(), 2));
        order2.addItem(new LineItem(catalog.findById("P-CCK").orElseThrow(), 1));
        System.out.println("Order #" + order2.getID() + " Total: " + order2.totalWithTax(10));
        order2.pay(new CardPayment("1234567812341234"));
    }
}
