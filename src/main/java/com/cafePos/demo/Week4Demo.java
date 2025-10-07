package com.cafePos.demo;

import com.cafePos.catalog.Catalog;
import com.cafePos.catalog.InMemoryCatalog;
import com.cafePos.common.Money;
import com.cafePos.common.SimpleProduct;
import com.cafePos.domain.*;
import com.cafePos.payment.CashPayment;

public class Week4Demo {
    public static void main(String[] args) {
        Catalog catalog = new InMemoryCatalog();
        catalog.add(new SimpleProduct("P-ESP", "Espresso", Money.of(2.50)));
        Order order = new Order(1005);
        order.register(new KitchenDisplay());
        order.register(new DeliveryDesk());
        order.register(new CustomerNotifier());
        order.addItem(new LineItem(catalog.findById("P-ESP").orElseThrow(), 1));
        order.pay(new CashPayment());
        order.markReady();
    }
}
