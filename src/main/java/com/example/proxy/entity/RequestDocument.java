package com.example.proxy.entity;

import com.example.proxy.entity.common.AuditingEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "document")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDocument extends AuditingEntity {

    @Column
    private String name;

    @Column
    private String type;

    @Column(name = "file", unique = false, nullable = false, length = 100000)
    private byte[] file;

    @Column
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_type_id")
    private DocumentType documentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id")
    private Request request;

}
