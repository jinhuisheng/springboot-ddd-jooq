package com.sh.role;

import com.sh.employee.ClientEnum;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static co.cantina.spring.jooq.sample.model.Tables.*;

/**
 * @author huisheng.jin
 * @version 2019/11/29.
 */
@Component
public class RoleRepresentationService {
    private final DSLContext dsl;

    @Autowired
    public RoleRepresentationService(DSLContext dsl) {
        this.dsl = dsl;
    }

    public RolePermissionAndMenuPresentation getPermissions(String roleId, String clientType) {
        List<RolePermissionRepresentation> rolePermissionRepresentations = getRolePermissionRepresentations(roleId);
        return new RolePermissionAndMenuPresentation(clientType,rolePermissionRepresentations);
    }

    private List<RolePermissionRepresentation> getRolePermissionRepresentations(String roleId) {
        return dsl.select()
                .from(SYS_ROLE_PERMISSION)
                .leftJoin(SYS_PERMISSION)
                .on(SYS_ROLE_PERMISSION.PERMISSION_ID.eq(SYS_PERMISSION.ID))
                .where(SYS_ROLE_PERMISSION.ROLE_ID.eq(roleId))
                .fetch()
                .map(record -> record.into(RolePermissionRepresentation.class));
    }
}
