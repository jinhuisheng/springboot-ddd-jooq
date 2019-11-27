package com.sh.employee;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

/**
 * @author huisheng.jin
 * @version 2019/11/27.
 */
@Getter
public class LoginCommand {
    /**
     * 账号
     */
    @NotBlank(message = "账号不能为空")
    private String accountNumber;
    /**
     * 工号
     */
    @NotBlank(message = "工号不能为空")
    private String workNumber;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;
}
