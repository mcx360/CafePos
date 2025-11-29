package com.cafePos.domain;

import com.cafePos.domain.common.Money;
import com.cafePos.domain.common.Product;
import com.cafePos.domain.common.Priced;

public final class LineItem {
    private final Product product;
    private final int quantity;
    public LineItem(Product product, int quantity) {
        if (product == null) throw new
                IllegalArgumentException("product required");
        if (quantity <= 0) throw new
                IllegalArgumentException("quantity must be > 0");
        this.product = product; this.quantity = quantity;
    }
    public Product product() { return product; }
    public int quantity() { return quantity; }
    public Money lineTotal() {
        Money unit = (product instanceof Priced) ? ((Priced) product).price() : product.basePrice();
        return unit.multiply(quantity);
    }
}
