package com.sh.employee;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import static com.sh.employee.QEmployee.*;

/**
 * @author huisheng.jin
 * @version 2019/11/27.
 */
public class EmployeeSpecification {
    public static Predicate byAccountNumberAndWorkNumber(String accountNumber, String workNumber) {
        return new BooleanBuilder()
                .and(employee.accountNumber.eq(accountNumber))
                .and(employee.workNumber.eq(workNumber));
    }
}
