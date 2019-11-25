package com.sh.order.exception;

import com.sh.common.exception.AppException;

import static com.sh.common.exception.ErrorCode.ORDER_CANNOT_BE_MODIFIED;
import static com.google.common.collect.ImmutableMap.of;

public class OrderCannotBeModifiedException extends AppException {
    public OrderCannotBeModifiedException(String id) {
        super(ORDER_CANNOT_BE_MODIFIED, of("orderId", id.toString()));
    }
}
