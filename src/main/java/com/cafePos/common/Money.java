package com.cafePos.common;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class Money implements Comparable<Money>{
    private final BigDecimal amount;

    public static Money of(double value) {
        //no check for negative value as I assume you can input negative money to use it to subtract from other sums
        return new Money(BigDecimal.valueOf(value));
    }
    public static Money zero() {
        return new Money(BigDecimal.ZERO);
    }

    private Money(BigDecimal a) {
        if (a == null) throw new IllegalArgumentException("amount required");
        this.amount = a.setScale(2, RoundingMode.HALF_UP);
    }

    public Money add(Money other){
        if(other.amount.intValue()<0) throw new IllegalArgumentException("Cant add negative numbers!");
        return new Money(this.amount.add(other.amount));
    }

    public Money multiply(int qty){
        if(qty<=0)throw new IllegalArgumentException("Cant multiply by negative numbers or zero!");
        return new Money(this.amount.multiply(BigDecimal.valueOf(qty)));
    }

    public BigDecimal getAmount(){
        return amount;
    }

    @Override
    public int compareTo(Money o) {
        return this.amount.compareTo(o.amount);
    }
}