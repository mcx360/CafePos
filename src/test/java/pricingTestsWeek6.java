import com.cafePos.domain.pricing.DiscountPolicy;
import com.cafePos.domain.pricing.FixedCouponDiscount;
import com.cafePos.domain.pricing.LoyaltyPercentDiscount;
import com.cafePos.domain.pricing.NoDiscount;
import com.cafePos.domain.common.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class DiscountPolicyTest {

    @Test
    void noDiscount_returnsZero() {
        DiscountPolicy policy = new NoDiscount();
        Money subtotal = Money.of(BigDecimal.valueOf(100).doubleValue());

        assertEquals(Money.zero(), policy.discountOf(subtotal));
    }

    @Test
    void loyaltyPercentDiscount_returnsCorrectValue() {
        DiscountPolicy policy = new LoyaltyPercentDiscount(10); // 10%
        Money subtotal = Money.of(BigDecimal.valueOf(200).doubleValue());

        Money expected = Money.of(BigDecimal.valueOf(20).doubleValue()); // 10% of 200
        assertEquals(expected, policy.discountOf(subtotal));
    }

    @Test
    void loyaltyPercentDiscount_constructorThrowsForNegativePercent() {
        assertThrows(IllegalArgumentException.class, () -> new LoyaltyPercentDiscount(-5));
    }

    @Test
    void fixedCouponDiscount_returnsFixedAmount() {
        DiscountPolicy policy = new FixedCouponDiscount(Money.of(BigDecimal.valueOf(30).doubleValue()));
        Money subtotal = Money.of(BigDecimal.valueOf(100).doubleValue());

        assertEquals(Money.of(BigDecimal.valueOf(30).doubleValue()), policy.discountOf(subtotal));
    }

    @Test
    void fixedCouponDiscount_capsAtSubtotal() {
        DiscountPolicy policy = new FixedCouponDiscount(Money.of(BigDecimal.valueOf(200).doubleValue()));
        Money subtotal = Money.of(BigDecimal.valueOf(100).doubleValue());

        assertEquals(subtotal, policy.discountOf(subtotal));
    }
}
