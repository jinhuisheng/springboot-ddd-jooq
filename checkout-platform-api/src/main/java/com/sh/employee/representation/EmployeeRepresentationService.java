package com.sh.employee.representation;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static co.cantina.spring.jooq.sample.model.Tables.*;


@Component
public class EmployeeRepresentationService {

    private final DSLContext dsl;

    @Autowired
    public EmployeeRepresentationService(DSLContext dsl) {
        this.dsl = dsl;
    }

    public EmployeeRepresentation byId(String id) {
        return dsl.select()
                .from(EMPLOYEE)
                .leftJoin(SYS_ROLE)
                .on(EMPLOYEE.ROLE_ID.eq(SYS_ROLE.ID))
                .where(EMPLOYEE.ID.eq(id))
                .fetch()
                .map(record -> record.into(EmployeeRepresentation.class))
                .get(0);
    }
}
