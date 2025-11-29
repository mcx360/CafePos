package com.cafePos.infra;

import com.cafePos.domain.*;
import java.util.*;

public final class InMemoryOrderRepository implements OrderRepository {
    private final Map<Long, Order> store = new HashMap<>();

    @Override
    public void save(Order order) { store.put(order.getID(), order); }

    @Override
    public Optional<Order> findById(long id) { return Optional.ofNullable(store.get(id)); }
}