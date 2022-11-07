package com.example.proxy.rest.entitymapper;

import com.example.proxy.entity.Request;
import com.example.proxy.rest.dto.RequestDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class RequestMapper {

    @Mappings({
            @Mapping(source = "status", target = "status", ignore = true)
    })
    public abstract RequestDto toDto(Request request);

    public abstract List<RequestDto> toDto(List<Request> requests);


    @InheritInverseConfiguration
    public abstract Request toEntity(RequestDto requestDto);

    public abstract List<Request> toEntity(List<RequestDto> requestDtos);


    @InheritInverseConfiguration
    public abstract Request updateEntityFromDto(RequestDto requestDto, @MappingTarget Request request);


}
