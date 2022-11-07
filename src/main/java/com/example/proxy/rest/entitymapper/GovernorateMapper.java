package com.example.proxy.rest.entitymapper;

import com.example.proxy.entity.Governorate;
import com.example.proxy.rest.dto.GovernorateDto;
import com.example.proxy.rest.entitymapper.common.JPAEntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GovernorateMapper extends JPAEntityMapper<Governorate, GovernorateDto> {
}
