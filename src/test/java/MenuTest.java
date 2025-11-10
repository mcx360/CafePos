import com.cafePos.menu.Menu;
import com.cafePos.menu.MenuComponent;
import com.cafePos.menu.MenuItem;
import com.cafePos.common.Money;
import com.cafePos.state.OrderFSM;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {

    @Test
    void depthFirstIterationAndVegetarianFilterWork() {
        Menu root = new Menu("ROOT");
        Menu drinks = new Menu("Drinks");
        Menu desserts = new Menu("Desserts");

        drinks.add(new MenuItem("Espresso", Money.of(2.50), true));
        drinks.add(new MenuItem("Latte", Money.of(3.90), true));
        desserts.add(new MenuItem("Cheesecake", Money.of(3.50), false));
        desserts.add(new MenuItem("Oat Cookie", Money.of(1.20), true));

        root.add(drinks);
        root.add(desserts);

        // Collect all names depth-first
        List<String> names = root.allItems().stream().map(MenuComponent::name).toList();

        assertTrue(names.contains("Espresso"));
        assertTrue(names.contains("Latte"));
        assertTrue(names.contains("Cheesecake"));
        assertTrue(names.contains("Oat Cookie"));

        // Vegetarian filter
        List<String> vegNames = root.vegetarianItems().stream().map(MenuItem::name).toList();
        assertEquals(List.of("Espresso", "Latte", "Oat Cookie"), vegNames);


    }


    @Test
    void orderFsmHappyPathTransitions() {
        OrderFSM fsm = new OrderFSM();

        assertEquals("NEW", fsm.status());

        fsm.pay();
        assertEquals("PREPARING", fsm.status());

        fsm.markReady();
        assertEquals("READY", fsm.status());

        fsm.deliver();
        assertEquals("DELIVERED", fsm.status());
    }
}
