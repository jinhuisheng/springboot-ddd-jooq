package com.sh.order;

import com.sh.common.ddd.DomainService;
import com.sh.order.model.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OrderPaymentService implements DomainService {
    private final OrderPaymentProxy paymentProxy;

    public OrderPaymentService(OrderPaymentProxy paymentProxy) {
        this.paymentProxy = paymentProxy;
    }

    public void pay(Order order, BigDecimal paidPrice) {
        order.pay(paidPrice);
        paymentProxy.pay(order.getId(), paidPrice);
    }
}
