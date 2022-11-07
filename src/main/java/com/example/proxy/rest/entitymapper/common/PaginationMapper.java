package com.example.proxy.rest.entitymapper.common;

import com.example.proxy.rest.dto.common.PaginationDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class PaginationMapper {

    public PaginationDto toPaginationDto(Page page) {
        PaginationDto paginationDto = new PaginationDto();
        paginationDto.setPageNo(page.getNumber());
        paginationDto.setPageSize(page.getSize());
        paginationDto.setTotalElements(page.getTotalElements());
        paginationDto.setTotalPages(page.getTotalPages());
        return paginationDto;
    }

}
