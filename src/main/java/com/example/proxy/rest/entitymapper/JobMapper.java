package com.example.proxy.rest.entitymapper;

import com.example.proxy.entity.Job;
import com.example.proxy.rest.dto.JobDto;
import com.example.proxy.rest.entitymapper.common.JPAEntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JobMapper extends JPAEntityMapper<Job, JobDto> {
}
