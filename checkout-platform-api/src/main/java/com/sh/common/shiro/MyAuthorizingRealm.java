package com.sh.common.shiro;

import com.sh.common.utils.JWTUtil;
import com.sh.employee.representation.EmployeeRepresentation;
import com.sh.employee.representation.EmployeeRepresentationService;
import com.sh.role.RoleApplicationService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class MyAuthorizingRealm extends AuthorizingRealm {

    private EmployeeRepresentationService employeeRepresentationService;
    private RoleApplicationService roleApplicationService;

    @Autowired
    public MyAuthorizingRealm(EmployeeRepresentationService employeeRepresentationService
            , RoleApplicationService roleApplicationService) {
        this.employeeRepresentationService = employeeRepresentationService;
        this.roleApplicationService = roleApplicationService;
    }

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTAuthenticationToken;
    }

    /**
     * 授权验证(是否可以访问资源)
     * Is the user allowed
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String roleId = JWTUtil.getRoleId(principals.toString());
        String clientType = JWTUtil.getClientType(principals.toString());
        Set<String> permissions = roleApplicationService.getPermissions(roleId, clientType).getPermissionList();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

    /**
     * 认证用户的身份
     * Allow access, retry authentication, or block access
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        String userId = JWTUtil.getUserId(token);
        if (StringUtils.isEmpty(userId)) {
            throw new AuthenticationException("token invalid");
        }

        EmployeeRepresentation employee = employeeRepresentationService.byId(userId);
        if (Objects.isNull(employee)) {
            throw new AuthenticationException("User didn't existed!");
        }

        if (!JWTUtil.verify(token, userId, employee.getPassword())) {
            throw new AuthenticationException("UserId or ClientType error");
        }

        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }

}
