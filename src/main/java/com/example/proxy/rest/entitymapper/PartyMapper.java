package com.example.proxy.rest.entitymapper;

import com.example.proxy.entity.Party;
import com.example.proxy.rest.dto.PartyDto;
import com.example.proxy.rest.entitymapper.common.JPAEntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PartyMapper extends JPAEntityMapper<Party, PartyDto> {
}
