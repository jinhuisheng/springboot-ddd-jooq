package com.sh.employee;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.sh.employee.Employee;
import com.sh.employee.QEmployee;

import javax.validation.constraints.NotBlank;

/**
 * @author huisheng.jin
 * @version 2019/11/27.
 */
public class EmployeeSpecification {
    public static Predicate byAccountNumberAndWorkNumber(String accountNumber, String workNumber) {
        QEmployee employee = QEmployee.employee;
        BooleanExpression eqAccountNumber = employee.accountNumber.eq(accountNumber);
        BooleanExpression eqWorkNumber = employee.accountNumber.eq(workNumber);

        return new BooleanBuilder()
                .and(eqAccountNumber)
                .and(eqWorkNumber);
    }
}
