package com.cafePos.domain.payment;

import com.cafePos.domain.Order;

public interface PaymentStrategy {
        void pay(Order order);
    }

