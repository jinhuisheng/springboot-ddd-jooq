package com.sh.order.model;

import com.sh.common.ddd.DomainService;
import com.sh.common.utils.UuidGenerator;
import org.springframework.stereotype.Component;

@Component
public class OrderIdGenerator implements DomainService {

    public String generate() {
        return UuidGenerator.newUuid();
    }
}
