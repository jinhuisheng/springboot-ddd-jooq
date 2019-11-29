package com.sh.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author huisheng.jin
 * @version 2019/11/29.
 */
@Component
public class RoleApplicationService {

    private RoleRepresentationService roleRepresentationService;

    @Autowired
    public RoleApplicationService(RoleRepresentationService roleRepresentationService) {
        this.roleRepresentationService = roleRepresentationService;
    }

    public Set<String> getPermissions(String roleId, String clientType) {
        return roleRepresentationService.getPermissions(roleId, clientType);
    }
}
