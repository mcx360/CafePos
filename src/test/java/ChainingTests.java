import com.cafePos.domain.Factory.ProductFactory;
import com.cafePos.domain.common.Money;
import com.cafePos.domain.common.Product;
import com.cafePos.domain.common.SimpleProduct;
import com.cafePos.domain.decorator.ExtraShot;
import com.cafePos.domain.decorator.OatMilk;
import com.cafePos.domain.decorator.SizeLarge;
import com.cafePos.domain.LineItem;
import com.cafePos.domain.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChainingTests {

    @Test
    void FactoryChainingVersusManualChaining(){
        Product factoryMade = new ProductFactory().create("ESP+SHOT+OAT+L");
        Product manuallyMade = new SizeLarge(new OatMilk(new ExtraShot(new SimpleProduct("P-ESP","Espresso", Money.of(2.50)))));
        assertEquals(factoryMade.name(),manuallyMade.name());
    }

    @Test
    void FactoryChainingVersusManualChainingPriceCheck(){
        Product factoryMade = new ProductFactory().create("ESP+SHOT+OAT+L");
        Product manuallyMade = new SizeLarge(new OatMilk(new ExtraShot(new SimpleProduct("P-ESP","Espresso", Money.of(2.50)))));
        assertEquals(factoryMade.basePrice(), manuallyMade.basePrice());
    }

    @Test
    void checkOrders(){
        Product factoryMade = new ProductFactory().create("ESP+SHOT+OAT+L");
        Product manuallyMade = new SizeLarge(new OatMilk(new ExtraShot(new SimpleProduct("P-ESP","Espresso", Money.of(2.50)))));

        LineItem factoryli = new LineItem(factoryMade, 1);
        LineItem manualli = new LineItem(manuallyMade, 1);

        Order factoryOrder = new Order(1);
        factoryOrder.addItem(factoryli);
        factoryOrder.taxAtPercent(10);

        Order manualOrder = new Order(2);
        manualOrder.addItem(manualli);
        manualOrder.taxAtPercent(10);

        assertEquals(factoryOrder.subtotal(), manualOrder.subtotal());

    }

}
