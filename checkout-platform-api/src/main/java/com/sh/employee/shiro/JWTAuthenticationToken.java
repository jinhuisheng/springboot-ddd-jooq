package com.sh.employee.shiro;

import org.apache.shiro.authc.AuthenticationToken;

public class JWTAuthenticationToken implements AuthenticationToken {

    // 密钥
    private String token;

    public JWTAuthenticationToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
