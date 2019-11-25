package com.sh.order.representation;

import com.sh.common.ddd.Representation;
import com.sh.order.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class OrderRepresentation implements Representation {
    private String id;
    private List<com.sh.representation.OrderItemRepresentation> items;
    private BigDecimal totalPrice;
    private OrderStatus status;
    private Instant createdAt;

    public OrderRepresentation(String id,
                               List<com.sh.representation.OrderItemRepresentation> items,
                               BigDecimal totalPrice,
                               OrderStatus status,
                               Instant createdAt) {
        this.id = id;
        this.items = items;
        this.totalPrice = totalPrice;
        this.status = status;
        this.createdAt = createdAt;
    }

    public OrderRepresentation() {
    }
}

