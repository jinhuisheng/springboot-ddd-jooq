package com.sh.role.model;

import com.sh.common.ddd.BaseEntity;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author huisheng.jin
 * @version 2019/11/28.
 */
@Entity
@Getter
@Table(name = "sys_role")
public class Role extends BaseEntity {
    /**
     * 角色名称
     */
    private String roleName;
    @ManyToMany
    @JoinTable(name = "sys_role_permission", joinColumns = {
            @JoinColumn(name = "role_id")
    }, inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private List<Permission> permissions = new ArrayList<>();
}
