package com.example.proxy.rest.entitymapper;

import com.example.proxy.entity.RequestDocument;
import com.example.proxy.rest.dto.RequestDocumentDto;
import com.example.proxy.rest.entitymapper.common.JPAEntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RequestDocumentMapper extends JPAEntityMapper<RequestDocument, RequestDocumentDto> {
}
