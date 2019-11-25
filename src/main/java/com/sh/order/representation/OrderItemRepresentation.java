package com.sh.representation;

import com.sh.common.ddd.Representation;

import java.math.BigDecimal;

public class OrderItemRepresentation implements Representation {
    private final String productId;
    private final int count;
    private final BigDecimal itemPrice;

    public OrderItemRepresentation(String productId, int count, BigDecimal itemPrice) {
        this.productId = productId;
        this.count = count;
        this.itemPrice = itemPrice;
    }
}
