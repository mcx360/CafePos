import com.cafePos.domain.common.Money;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTests {

    @Test
    void testAddition() {
        Money m1 = Money.of(2.00);
        Money m2 = Money.of(3.00);
        assertEquals(Money.of(5.00), m1.add(m2));
    }

    @Test
    void testMultiply() {
        Money price = Money.of(2.50);
        Money total = price.multiply(4);
        assertEquals(Money.of(10.00), total);
    }

    @Test
    void testZeroMoney() {
        assertEquals(Money.of(0.00), Money.zero());
    }

    @Test
    void testNegativeNotAllowed() {
        assertThrows(IllegalArgumentException.class, () -> Money.of(-1.0));
    }
}