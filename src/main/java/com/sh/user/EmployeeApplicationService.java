package com.sh.user;

import com.sh.user.representation.EmployeeRepresentation;
import com.sh.user.representation.EmployeeRepresentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * @author huisheng.jin
 * @version 2019/11/26.
 */
@Service
public class EmployeeApplicationService {

    private EmployeeRepresentationService employeeRepresentationService;

    @Autowired
    public EmployeeApplicationService(EmployeeRepresentationService employeeRepresentationService) {
        this.employeeRepresentationService = employeeRepresentationService;
    }

    @Transactional(readOnly = true)
    public EmployeeRepresentation byId(String id) {
        return employeeRepresentationService.byId(id);
    }

    public Set<String> getPermissions(String userId) {
        //todo 获取权限
        Set<String> permissions = new HashSet<>();
        permissions.add("view");
        permissions.add("edit");
        return permissions;
    }
}
