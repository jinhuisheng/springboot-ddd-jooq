package com.sh.role;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author huisheng.jin
 * @version 2019/11/29.
 */
@Component
public class RoleRepresentationService {
    public Set<String> getPermissions(String roleId, String clientType) {
        Set<String> permissions = new HashSet<>();
        permissions.add("article:add");
        permissions.add("edit");
        return permissions;
    }
}
