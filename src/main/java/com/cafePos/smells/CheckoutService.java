package com.cafePos.smells;

import com.cafePos.Factory.ProductFactory;
import com.cafePos.common.Money;
import com.cafePos.common.Product;
import com.cafePos.domain.Order;
import com.cafePos.payment.PaymentStrategy;
import com.cafePos.pricing.PricingService;
import com.cafePos.pricing.ReceiptPrinter;


// Adapt to your Week-3 signature; if your strategy expects an Order,pass the real one here.
// If your strategy prints based on totals, wrap in a tiny adapter and call after pricing.
public final class CheckoutService {
    private final ProductFactory factory;
    private final PricingService pricing;
    private final ReceiptPrinter printer;
    private final int taxPercent;
    public CheckoutService(ProductFactory factory, PricingService
            pricing, ReceiptPrinter printer, int taxPercent) {
        this.factory = factory;
        this.pricing = pricing;
        this.printer = printer;
        this.taxPercent = taxPercent;
    }
    public String checkout(String recipe, int qty, PaymentStrategy strategy, Order order) {
        Product product = factory.create(recipe);
        if (qty <= 0) qty = 1;
        Money unit = (product instanceof com.cafePos.common.Priced p)
                ? p.price() : product.basePrice();
        Money subtotal = unit.multiply(qty);
        var result = pricing.price(subtotal);
        if (strategy != null && order != null) {
            strategy.pay(order);
        }

        return printer.format(recipe, qty, result, taxPercent);
    }

    public String checkout(String recipe, int qty) {
        return checkout(recipe, qty, null, null);
    }

}
