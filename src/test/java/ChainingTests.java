import com.cafePos.Factory.ProductFactory;
import com.cafePos.common.Money;
import com.cafePos.common.Priced;
import com.cafePos.common.Product;
import com.cafePos.common.SimpleProduct;
import com.cafePos.decorator.ExtraShot;
import com.cafePos.decorator.OatMilk;
import com.cafePos.decorator.SizeLarge;
import com.cafePos.domain.LineItem;
import com.cafePos.domain.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChainingTests {

    @Test
    void FactoryChainingVersusManualChaining(){
        Product factoryMade = new ProductFactory().create("ESP+SHOT+OAT+L");
        Product manuallyMade = new SizeLarge(new OatMilk(new ExtraShot(new SimpleProduct("P-ESP","Espresso", Money.of(2.50)))));
        assertEquals(factoryMade.name(), manuallyMade.name());

    }
}
