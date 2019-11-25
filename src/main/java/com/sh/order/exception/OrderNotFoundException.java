package com.sh.order.exception;

import com.sh.common.exception.AppException;
import com.google.common.collect.ImmutableMap;

import static com.sh.common.exception.ErrorCode.ORDER_NOT_FOUND;

public class OrderNotFoundException extends AppException {
    public OrderNotFoundException(String orderId) {
        super(ORDER_NOT_FOUND, ImmutableMap.of("orderId", orderId.toString()));
    }
}
