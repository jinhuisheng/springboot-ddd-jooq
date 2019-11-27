package com.sh.order.model;

import com.sh.common.ddd.Factory;
import com.sh.common.utils.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderFactory implements Factory {
    private final OrderIdGenerator idGenerator;

    @Autowired
    public OrderFactory(OrderIdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    public Order create(List<OrderItem> items, Address address) {
        String orderId = idGenerator.generate();
        return Order.create(orderId, items, address);
    }
}
