package com.sh.order.representation;

import co.cantina.spring.jooq.sample.model.Tables;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class OrderRepresentationService {

    private final DSLContext dsl;

    @Autowired
    public OrderRepresentationService(DSLContext dsl) {
        this.dsl = dsl;
    }

    public OrderRepresentation getBooks(String id) {
        return dsl.selectFrom(Tables.ORDERS)
                .where()
                .fetch()
                .map(record->record.into(OrderRepresentation.class))
                .get(0);
    }
}
