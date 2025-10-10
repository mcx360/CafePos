package com.cafePos.demo;

import com.cafePos.Factory.ProductFactory;
import com.cafePos.common.Product;
import com.cafePos.domain.LineItem;
import com.cafePos.domain.Order;

public class week5Demo {
    public static void main(String[] args){
        ProductFactory factory = new ProductFactory();
        Product p1 = factory.create("ESP+SHOT+OAT"); // Espresso+ Extra Shot + Oat
        Product p2 = factory.create("LAT+L"); // Large Latte
        Order order = new Order(1098);
        order.addItem(new LineItem(p1, 1));
        order.addItem(new LineItem(p2, 2));
        System.out.println("Order #" + order.getID());
        for (LineItem li : order.getItems()){
            System.out.println(" - " + li.product().name() + " x" + li.quantity() + " = " + li.lineTotal());
        }
        System.out.println("Subtotal: " + order.subtotal());
        System.out.println("Tax (10%): " + order.taxAtPercent(10));
        System.out.println("Total: " + order.totalWithTax(10));
    }
}
