package com.sh.order;

import com.sh.order.command.CreateOrderCommand;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
    private final OrderApplicationService orderApplicationService;

    public OrderController(OrderApplicationService orderApplicationService) {
        this.orderApplicationService = orderApplicationService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public String createOrder(@RequestBody @Valid CreateOrderCommand command) {
        return orderApplicationService.createOrder(command);
    }

    @GetMapping("/{id}")
    public Object byId(@PathVariable(name = "id") String id) {
        return orderApplicationService.byId(id);
    }
}
