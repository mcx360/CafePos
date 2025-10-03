import com.cafePos.common.SimpleProduct;
import com.cafePos.common.Money;
import com.cafePos.domain.LineItem;
import com.cafePos.domain.Order;
import com.cafePos.domain.OrderObserver;
import com.cafePos.payment.CashPayment;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ObserverTest {

    @Test
     void observers_notified_on_item_add() {
        var p = new SimpleProduct("A", "A", Money.of(2));
        var o = new Order(1);
        o.addItem(new LineItem(p, 1)); // baseline
        List<String> events = new ArrayList<>();
        o.register((order, evt) -> events.add(evt));
        o.addItem(new LineItem(p, 1));
        assertTrue(events.contains("Item added"));
    }

    @Test
    void observers_notified_on_payment(){
        var p = new SimpleProduct("A", "A", Money.of(2));
        var o = new Order(1);
        o.addItem(new LineItem(p, 1)); // baseline
        List<String> events = new ArrayList<>();
        o.register((order, evt) -> events.add(evt));
        o.addItem(new LineItem(p, 1));
        o.pay(new CashPayment());
        assertTrue(events.contains("Paid"));
    }

}
