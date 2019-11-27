package com.sh.order.exception;

import com.sh.common.exception.AppException;

import static com.sh.common.exception.ErrorCode.PAID_PRICE_NOT_SAME_WITH_ORDER_PRICE;
import static com.google.common.collect.ImmutableMap.of;

public class PaidPriceNotSameWithOrderPriceException extends AppException {
    public PaidPriceNotSameWithOrderPriceException(String id) {
        super(PAID_PRICE_NOT_SAME_WITH_ORDER_PRICE, of("orderId", id.toString()));
    }
}
