package com.example.proxy.entity;

import com.example.proxy.entity.common.JPAEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordResetToken extends JPAEntity {

    @Column
    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;

    @Column
    private Date expiryDate;
}
