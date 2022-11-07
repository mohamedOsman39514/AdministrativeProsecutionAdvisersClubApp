package com.example.proxy.rest.entitymapper;

import com.example.proxy.entity.DocumentType;
import com.example.proxy.rest.dto.DocumentTypeDto;
import com.example.proxy.rest.entitymapper.common.JPAEntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DocumentTypeMapper extends JPAEntityMapper<DocumentType, DocumentTypeDto> {
}
