package com.sh.order;

import com.sh.common.ddd.ApplicationService;
import com.sh.order.command.CreateOrderCommand;
import com.sh.order.model.OrderFactory;
import com.sh.order.model.OrderItem;
import com.sh.order.representation.OrderRepresentation;
import com.sh.order.representation.OrderRepresentationService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class OrderApplicationService implements ApplicationService {
    private final OrderRepresentationService orderRepresentationService;
    private final OrderJdbcRepository orderJdbcRepository;
    private final OrderFactory orderFactory;
    private final OrderPaymentService orderPaymentService;
    private final OrderRepository orderRepository;

    public OrderApplicationService(OrderRepresentationService orderRepresentationService,
                                   OrderJdbcRepository orderJdbcRepository,
                                   OrderFactory orderFactory,
                                   OrderPaymentService orderPaymentService, OrderRepository orderRepository) {
        this.orderRepresentationService = orderRepresentationService;
        this.orderJdbcRepository = orderJdbcRepository;
        this.orderFactory = orderFactory;
        this.orderPaymentService = orderPaymentService;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public String createOrder(CreateOrderCommand command) {
        List<OrderItem> items = command.getItems().stream()
                .map(item -> OrderItem.create(item.getProductId(),
                        item.getCount(),
                        item.getItemPrice()))
                .collect(Collectors.toList());

        com.sh.order.model.Order order = orderFactory.create(items, command.getAddress());
        orderRepository.save(order);
//        orderJdbcRepository.save(order);
        return order.getId();
    }

//    @Transactional(readOnly = true)
//    public com.daqinshang.order.representation.OrderRepresentation byId(String id) {
//        com.daqinshang.order.model.Order order = orderJdbcRepository.byId(id);
//        return orderRepresentationService.toRepresentation(order);
//    }

    @Transactional(readOnly = true)
    public OrderRepresentation byId(String id) {
        return orderRepresentationService.getBooks(id);
//        return orderRepresentationService.toRepresentation(order);
    }


}
