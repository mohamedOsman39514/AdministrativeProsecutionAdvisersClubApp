package com.example.proxy.rest.entitymapper;

import com.example.proxy.entity.Status;
import com.example.proxy.rest.dto.StatusDto;
import com.example.proxy.rest.entitymapper.common.JPAEntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StatusMapper extends JPAEntityMapper<Status, StatusDto> {
}
