package com.example.proxy.entity.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
@EqualsAndHashCode(of = {"name"}, callSuper = true)
@ToString(of = {"name"}, callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class LookupEntity extends JPAEntity {

    private String name;

}
