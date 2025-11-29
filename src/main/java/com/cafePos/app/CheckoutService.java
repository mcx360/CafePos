package com.cafePos.app;

import com.cafePos.domain.*;
import com.cafePos.domain.pricing.PricingService;

public final class CheckoutService {
    private final OrderRepository orders;
    private final PricingService pricing;
    public CheckoutService(OrderRepository orders, PricingService pricing) {
        this.orders = orders; this.pricing = pricing;
    }
    /** Returns a receipt string; does NOT print. */
    public String checkout(long orderId, int taxPercent) {
        Order order = orders.findById(orderId).orElseThrow();
        var pr = pricing.price(order.subtotal());
        return new ReceiptFormatter().format(orderId, order.getItems(), pr, taxPercent);
    }
}