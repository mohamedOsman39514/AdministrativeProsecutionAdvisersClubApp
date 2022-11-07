package com.example.proxy.rest.entitymapper;

import com.example.proxy.entity.User;
import com.example.proxy.rest.dto.UserDto;
import com.example.proxy.rest.entitymapper.common.JPAEntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends JPAEntityMapper<User, UserDto> {
}
