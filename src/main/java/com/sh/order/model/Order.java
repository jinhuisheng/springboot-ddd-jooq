package com.sh.order.model;

import com.sh.common.ddd.AggregateRoot;
import com.sh.common.utils.Address;
import com.sh.order.exception.OrderCannotBeModifiedException;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.sh.order.model.OrderStatus.CREATED;
import static com.sh.order.model.OrderStatus.PAID;
import static java.math.BigDecimal.ZERO;
import static java.time.Instant.now;

@Entity
@Table(name = "orders")
public class Order implements AggregateRoot {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Set<OrderItem> items = new HashSet<>();
    private BigDecimal totalPrice;
    private OrderStatus status;
    private Instant createdAt;

    private Order() {
    }

    private Order(String id, List<OrderItem> items, Address address) {
        this.id = id;
        this.items.addAll(items);
        this.totalPrice = calculateTotalPrice();
        this.status = CREATED;
//        this.address = address;
        this.createdAt = now();
    }

    public static Order create(String id, List<OrderItem> items, Address address) {
        return new Order(id, items, address);
    }

    private BigDecimal calculateTotalPrice() {
        return items.stream()
                .map(OrderItem::totalPrice)
                .reduce(ZERO, BigDecimal::add);

    }

    public void changeProductCount(String productId, int count) {
        if (this.status == PAID) {
            throw new OrderCannotBeModifiedException(this.id);
        }

        OrderItem orderItem = retrieveItem(productId);

        orderItem.updateCount(count);

        this.totalPrice = calculateTotalPrice();
    }

    private OrderItem retrieveItem(String productId) {
        return items.stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst().orElse(null);
    }

    public void pay(BigDecimal paidPrice) {
        if (!this.totalPrice.equals(paidPrice)) {
//            throw new PaidPriceNotSameWithOrderPriceException(id);
        }
        this.status = PAID;
    }

    public void changeAddressDetail(String detail) {
        if (this.status == PAID) {
            throw new OrderCannotBeModifiedException(this.id);
        }

//        this.address = this.address.changeDetailTo(detail);
    }

    public String getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

//    public Address getAddress() {
//        return address;
//    }
}
