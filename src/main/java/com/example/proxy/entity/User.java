package com.example.proxy.entity;

import com.example.proxy.entity.common.AuditingEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends AuditingEntity {

    @Column
    private String name;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @Column
    private String phone;

    @Column
    private String address;

    @Column(unique = true)
    private String nationalId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "party_id")
    private Party party;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "governorate_id")
    private Governorate governorate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "roles_id")
    private List<Role> roles;

}
