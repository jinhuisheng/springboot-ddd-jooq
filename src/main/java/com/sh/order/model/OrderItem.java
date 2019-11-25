package com.sh.order.model;

import com.sh.common.ddd.Entity;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@javax.persistence.Entity
@Table(name="orders_item")
@Data
public class OrderItem implements Entity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    protected String id;

    private String productId;
    private int count;
    private BigDecimal itemPrice;

    private OrderItem() {
    }

    private OrderItem(String productId, int count, BigDecimal itemPrice) {
        this.productId = productId;
        this.count = count;
        this.itemPrice = itemPrice;
    }

    public static OrderItem create(String productId, int count, BigDecimal itemPrice) {
        return new OrderItem(productId, count, itemPrice);
    }

    BigDecimal totalPrice() {
        return itemPrice.multiply(BigDecimal.valueOf(count));
    }

    public void updateCount(int count) {
        this.count = count;
    }

    public String getProductId() {
        return productId;
    }

    public int getCount() {
        return count;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }
}
