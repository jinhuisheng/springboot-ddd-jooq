package com.sh.common.utils;

import lombok.Getter;

/**
 * @author huisheng.jin
 * @version 2019/11/29.
 */
@Getter
public class JWTSignInput {
    private final String userId;
    private final String secret;
    private final String clientType;
    private final String roleId;

    public JWTSignInput(String userId, String roleId, String secret, String clientType) {

        this.userId = userId;
        this.secret = secret;
        this.clientType = clientType;
        this.roleId = roleId;
    }
}
