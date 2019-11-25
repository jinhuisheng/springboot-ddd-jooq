package com.sh.order;

import com.sh.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author huisheng.jin
 * @version 2019/11/25.
 */
public interface OrderRepository extends JpaRepository<Order, String> {

}
