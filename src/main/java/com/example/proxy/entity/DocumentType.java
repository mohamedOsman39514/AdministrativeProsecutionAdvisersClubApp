package com.example.proxy.entity;

import com.example.proxy.entity.common.AuditingEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentType extends AuditingEntity {

    @Column
    private String name;

}
