package com.cafePos.demo;

import com.cafePos.common.Money;
import com.cafePos.common.SimpleProduct;
import com.cafePos.catalog.Catalog;
import com.cafePos.catalog.InMemoryCatalog;
import com.cafePos.domain.LineItem;
import com.cafePos.domain.Order;

public final class Week2Demo {
        public static void main(String[] args) {
            Catalog catalog = new InMemoryCatalog();
            catalog.add(new SimpleProduct("P-ESP", "Espresso",
                    Money.of(2.50)));
            catalog.add(new SimpleProduct("P-CCK", "Chocolate Cookie", Money.of(3.50)));
                    Order order = new Order(OrderIds.next());
            order.addItem(new LineItem(catalog.findById("P-ESP").orElseThrow(), 2));
                    order.addItem(new LineItem(catalog.findById("P-CCK").orElseThrow(), 1));
            int taxPct = 10;
            System.out.println("Order #" + order.id());
            System.out.println("Items: " + order.items().size());
            System.out.println("Subtotal: " + order.subtotal());
            System.out.println("Tax (" + taxPct + "%): " + Order.taxAtPercent(taxPct));
            System.out.println("Total: " + Order.totalWithTax(taxPct));
        }
    }

