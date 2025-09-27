import com.cafePos.common.Money;
import com.cafePos.common.SimpleProduct;
import com.cafePos.domain.LineItem;
import com.cafePos.domain.Order;
import com.cafePos.payment.PaymentStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PaymentStrategyTest {

    @Test
    void payment_strategy_called() {
        var p = new SimpleProduct("A", "A", Money.of(5));
        var order = new Order(42);
        order.addItem(new LineItem(p, 1));
        final boolean[] called = {false};
        PaymentStrategy fake = o -> called[0] = true;
        order.pay(fake);
        assertTrue(called[0], "Payment strategy should be called");
    }
}
