package com.sh.employee;

import com.querydsl.core.types.Predicate;
import com.sh.common.logging.AutoNamingLoggerFactory;
import com.sh.common.utils.JWTSignInput;
import com.sh.common.utils.JWTUtil;
import com.sh.common.utils.RestResult;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author huisheng.jin
 * @version 2019/11/26.
 */
@Service
public class EmployeeApplicationService {

    private Logger logger = AutoNamingLoggerFactory.getLogger();
    private EmployeeRepository employeeRepository;


    @Autowired
    public EmployeeApplicationService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public RestResult login(LoginCommand loginCommand) {
        Predicate specification = EmployeeSpecification.byAccountNumberAndWorkNumber(
                loginCommand.getAccountNumber(), loginCommand.getWorkNumber());

        Optional<Employee> employee = employeeRepository.findOne(specification);
        if (!employee.isPresent()) {
            return RestResult.failure("用户不存在");
        }
        Employee loginEmployee = employee.get();
        if (!loginEmployee.isPasswordRight(loginCommand.getPassword())) {
            return RestResult.failure("输入账号/工号/密码不正确");
        }
        JWTSignInput signInput = new JWTSignInput(loginEmployee.getId(), loginEmployee.getRoleId()
                , loginEmployee.getPassword(), loginCommand.getClientType());
        return RestResult.success(JWTUtil.sign(signInput));
    }
}
