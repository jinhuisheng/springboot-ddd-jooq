package com.sh.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public RolePermissionAndMenuPresentation getPermissions(String roleId, String clientType) {
        return roleRepresentationService.getPermissions(roleId, clientType);
    }
}
