package com.sh.user.representation;

import com.sh.common.ddd.Representation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRepresentation implements Representation {
    private String id;
    /**
     * 工号
     */
    private String workNumber;
    /**
     * 账号
     */
    private String accountNumber;
    /**
     * 员工姓名
     */
    private String employeeName;
    /**
     * 性别:0,女；1，男
     */
    private Integer sex;
    /**
     * 角色id
     */
    private String roleId;
    /**
     * 密码(前后端加密 todo)
     */
    private String password;
    /**
     * 门店Id（所属门店）
     */
    private Long shopId;
    /**
     * 备注信息
     */
    private String note;
    /**
     * 手机号码
     */
    private String phoneNumber;
    /**
     * 商户Id
     */
    private Long merchantId;
}

