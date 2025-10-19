package com.cafePos.common;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class Money implements Comparable<Money>{
    private final BigDecimal amount;

    public static Money of(double value) {
        if(value<0) throw new IllegalArgumentException("Money amount cant be a negative!");
        return new Money(BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP));
    }

    public static Money zero() {
        return new Money(BigDecimal.ZERO);
    }

    private Money(BigDecimal a) {
        if (a == null) throw new IllegalArgumentException("amount required!");
        this.amount = a.setScale(2, RoundingMode.HALF_UP);
    }

    public Money add(Money other){
        if(other.amount.doubleValue()<0) throw new IllegalArgumentException("Cant add negative numbers!");
        return new Money(this.amount.add(other.amount));
    }

    public Money multiply(int qty){
        if(qty<0)throw new IllegalArgumentException("Cant multiply by negative numbers!");
        return new Money(this.amount.multiply(BigDecimal.valueOf(qty)));
    }

    public Money multiply(double factor){
        if(factor < 0) throw new IllegalArgumentException("Cant multiply by negative numbers!");
        return new Money(this.amount.multiply(BigDecimal.valueOf(factor)));
    }

    public BigDecimal getAmount(){
        return amount;
    }

    @Override
    public int compareTo(Money o) {
        return this.amount.compareTo(o.amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money)) return false;
        Money money = (Money) o;
        return amount.equals(money.amount);
    }

    @Override
    public int hashCode() {
        return amount.hashCode();
    }

    @Override
    public String toString() {

        return amount.setScale(2, java.math.RoundingMode.HALF_UP).toPlainString();
    }

}