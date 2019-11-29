package com.sh.role;

import com.sh.employee.ClientEnum;
import lombok.Getter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author huisheng.jin
 * @version 2019/11/29.
 */
@Getter
public class RolePermissionAndMenuPresentation {
    private Set<String> menuList;
    private Set<String> permissionList;

    public RolePermissionAndMenuPresentation(String clientType, List<RolePermissionRepresentation> rolePermissionRepresentations) {
        this.permissionList = filterPermissionList(clientType, rolePermissionRepresentations);
        this.menuList = filterMenuList(rolePermissionRepresentations);
    }

    private Set<String> filterMenuList(List<RolePermissionRepresentation> rolePermissionRepresentations) {
        return rolePermissionRepresentations
                .stream()
                .map(RolePermissionRepresentation::getMenuCode)
                .collect(Collectors.toSet());
    }

    private Set<String> filterPermissionList(String clientType
            , List<RolePermissionRepresentation> rolePermissionRepresentations) {
        return rolePermissionRepresentations
                .stream()
                .filter(item -> item.getPermissionType().equals(getPermissionType(clientType)))
                .map(RolePermissionRepresentation::getPermissionCode)
                .collect(Collectors.toSet());
    }

    private String getPermissionType(String clientType) {
        if (clientType.equals(ClientEnum.WINDOWS_CLIENT.toString())
                || clientType.equals(ClientEnum.ANDROID_CLIENT.toString())) {
            return "client";
        } else {
            return "browser";
        }
    }

}
