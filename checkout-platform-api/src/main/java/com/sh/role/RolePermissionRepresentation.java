package com.sh.role;

import lombok.Getter;

/**
 * @author huisheng.jin
 * @version 2019/11/29.
 */
@Getter
public class RolePermissionRepresentation {
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
    /**
     * 权限类型(客户端:client;浏览器:browser)
     */
    private String permissionType;

}
