package com.sh.employee.representation;

import co.cantina.spring.jooq.sample.model.Tables;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class EmployeeRepresentationService {

    private final DSLContext dsl;

    @Autowired
    public EmployeeRepresentationService(DSLContext dsl) {
        this.dsl = dsl;
    }

    public EmployeeRepresentation byId(String id) {
        return dsl.selectFrom(Tables.EMPLOYEE)
                .where(Tables.EMPLOYEE.ID.eq(id))
                .fetch()
                .map(record -> record.into(EmployeeRepresentation.class))
                .get(0);
    }
}
