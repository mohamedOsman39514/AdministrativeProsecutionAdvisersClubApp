package com.example.proxy.rest.entitymapper;

import com.example.proxy.entity.ServiceType;
import com.example.proxy.rest.dto.ServiceTypeDto;
import com.example.proxy.rest.entitymapper.common.JPAEntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceTypeMapper extends JPAEntityMapper<ServiceType, ServiceTypeDto> {
}
