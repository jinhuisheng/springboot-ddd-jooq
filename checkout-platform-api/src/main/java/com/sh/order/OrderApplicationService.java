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
    private final OrderFactory orderFactory;
    private final OrderRepository orderRepository;

    public OrderApplicationService(OrderRepresentationService orderRepresentationService,
                                   OrderFactory orderFactory,
                                   OrderRepository orderRepository) {
        this.orderRepresentationService = orderRepresentationService;
        this.orderFactory = orderFactory;
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
        return order.getId();
    }

    @Transactional(readOnly = true)
    public OrderRepresentation byId(String id) {
        return orderRepresentationService.getBooks(id);
    }


}
