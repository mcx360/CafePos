import com.cafePos.domain.common.SimpleProduct;
import com.cafePos.domain.common.Money;
import com.cafePos.domain.LineItem;
import com.cafePos.domain.Order;
import com.cafePos.domain.OrderObserver;
import com.cafePos.domain.payment.CashPayment;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
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


    @Test
    void order_notifies_multiple_observers_on_ready() {
        var order = new Order(2);
        var a = new ArrayList<String>();
        var b = new ArrayList<String>();

        order.register((o, evt) -> a.add(evt));
        order.register((o, evt) -> b.add(evt));

        order.markReady();

        assertTrue(a.contains("ready"));
        assertTrue(b.contains("ready"));
    }

    @Test
    void unregistered_observer_does_not_receive_events() {
        var product = new SimpleProduct("P2", "Latte", Money.of(3.0));
        var order = new Order(3);
        var events = new ArrayList<String>();

        OrderObserver fake = (o, evt) -> events.add(evt);
        order.register(fake);
        order.unregister(fake);

        order.addItem(new LineItem(product, 1));
        assertFalse(events.contains("itemAdded"));
    }

}
