import com.cafePos.domain.common.Money;
import com.cafePos.domain.common.SimpleProduct;
import com.cafePos.domain.LineItem;
import com.cafePos.domain.Order;
import com.cafePos.domain.payment.CardPayment;
import com.cafePos.domain.payment.PaymentStrategy;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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

    @Test
    void cardPayment_masksCardNumber() {
        var p1 = new SimpleProduct("A", "Coffee", Money.of(2.50));
        var p2 = new SimpleProduct("B", "Cookie", Money.of(3.50));
        var order = new Order(1);
        order.addItem(new LineItem(p1, 2)); // 5.00
        order.addItem(new LineItem(p2, 1));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        order.pay(new CardPayment("12589099"));

        String printed = out.toString().trim();
        assertTrue(printed.contains("****9099"),
                "Card number should be masked except last 4 digits");
    }
}
