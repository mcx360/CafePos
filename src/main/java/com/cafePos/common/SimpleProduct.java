package com.cafePos.common;

public class SimpleProduct implements Product{

    private final String id;
    private final String name;
    private final Money basePrice;

    public SimpleProduct(String id, String name, Money basePrice){
        if(basePrice.getAmount().doubleValue()<0.0) throw new IllegalArgumentException("amount must be grater than zero!");
        this.id = id;
        this.name = name;
        this.basePrice = basePrice;
    }

    @Override public String id(){return id;}
    @Override public String name(){ return name;}
    @Override public Money basePrice() { return basePrice;}
}
