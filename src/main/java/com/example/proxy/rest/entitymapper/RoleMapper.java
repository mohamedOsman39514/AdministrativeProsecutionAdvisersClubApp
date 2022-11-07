package com.example.proxy.rest.entitymapper;

import com.example.proxy.entity.Role;
import com.example.proxy.rest.dto.RoleDto;
import com.example.proxy.rest.entitymapper.common.JPAEntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper extends JPAEntityMapper<Role, RoleDto> {
}
