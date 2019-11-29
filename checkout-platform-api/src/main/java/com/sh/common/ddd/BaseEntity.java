package com.sh.common.ddd;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 使用jpa的entity都应该从这个接口进行继承
 *
 * @author huisheng.jin
 * @version 2019/1/25
 */
@Data
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
@EqualsAndHashCode
public abstract class BaseEntity implements com.sh.common.ddd.Entity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @CreatedDate
    @Column
    private LocalDateTime createTime;

    @LastModifiedDate
    @Column
    private LocalDateTime updateTime;

    /**
     * 是否有效(软删除)
     */
    @Column(nullable = false, name = "is_delete")
    private Integer isDelete = 0;

}