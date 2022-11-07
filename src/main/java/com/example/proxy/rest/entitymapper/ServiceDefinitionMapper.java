package com.example.proxy.rest.entitymapper;

import com.example.proxy.entity.ServiceDefinition;
import com.example.proxy.rest.dto.ServiceDefinitionDto;
import com.example.proxy.rest.entitymapper.common.JPAEntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceDefinitionMapper extends JPAEntityMapper<ServiceDefinition, ServiceDefinitionDto> {
}
