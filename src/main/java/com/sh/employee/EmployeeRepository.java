package com.sh.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author huisheng.jin
 * @version 2019/11/26.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String>, QuerydslPredicateExecutor<Employee> {
}
