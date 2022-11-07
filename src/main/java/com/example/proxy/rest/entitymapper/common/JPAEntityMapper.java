package com.example.proxy.rest.entitymapper.common;

import com.example.proxy.entity.common.JPAEntity;
import com.example.proxy.rest.dto.common.RestDto;

import java.util.List;

public interface JPAEntityMapper <T extends JPAEntity, S extends RestDto>{

    T toEntity(S s);

    S toDto(T t);

    List<T> toEntity(List<S> dtoList);

    List<S> toDto(List<T> dtoList);

}
