package com.cafePos.payment;

import com.cafePos.domain.Order;

public interface PaymentStrategy {
        void pay(Order order);
    }

