package com.sh.role.model;

import com.sh.common.ddd.BaseEntity;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author huisheng.jin
 * @version 2019/11/28.
 */
@Entity
@Getter
@Table(name = "permission")
public class Permission extends BaseEntity {
    /**
     * 权限名称
     */
    private String permissionName;
    /**
     * 权限code
     */
    private String permissionCode;
    /**
     * 菜单code
     */
    private String menuCode;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 是否必须，添加菜单权限时必须要赋值。(1,必须;2,不必须)
     */
    private Integer required;
}
