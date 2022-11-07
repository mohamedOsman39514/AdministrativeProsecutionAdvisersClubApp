package com.example.proxy.entity;

import com.example.proxy.entity.common.LookupEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
public class Party extends LookupEntity {
}
