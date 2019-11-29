package com.sh.employee;

import com.sh.common.ddd.AggregateRoot;
import com.sh.common.ddd.BaseEntity;
import lombok.Getter;

import javax.persistence.*;

/**
 * 员工
 *
 * @author huisheng.jin
 * @version 2019/11/26.
 */
@Entity
@Table(name = "employee")
@Getter
public class Employee extends BaseEntity implements AggregateRoot {
    /**
     * 账号
     */
    private String accountNumber;
    /**
     * 工号
     */
    private String workNumber;
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

    public boolean isPasswordRight(String password) {
        return this.password.equals(password);
    }
}
