package com.sh.common.ddd;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Value Object
 *
 * @author huisheng.jin
 * @version 2019/1/25
 */
@Data
@MappedSuperclass
@EqualsAndHashCode
public abstract class BaseValueObject<T> implements com.sh.common.ddd.ValueObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected T id;

    @CreatedDate
    @Column
    private LocalDateTime createTime;
}