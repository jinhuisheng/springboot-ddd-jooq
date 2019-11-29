package com.sh.role.model;

import com.sh.common.ddd.BaseEntity;
import lombok.Getter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * @author huisheng.jin
 * @version 2019/11/28.
 */
@Entity
@Getter
@Table(name = "role")
public class Role extends BaseEntity {
    /**
     * 角色名称
     */
    private String roleName;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "role_id")
    private List<Permission> permissions;
}
