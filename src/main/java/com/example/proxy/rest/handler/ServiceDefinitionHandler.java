package com.example.proxy.rest.handler;

import com.example.proxy.entity.ServiceDefinition;
import com.example.proxy.rest.dto.ServiceDefinitionDto;
import com.example.proxy.rest.dto.common.PaginationReultDto;
import com.example.proxy.rest.entitymapper.common.PaginationMapper;
import com.example.proxy.rest.entitymapper.ServiceDefinitionMapper;
import com.example.proxy.rest.exception.ResourceNotFoundException;
import com.example.proxy.service.ServiceDefinitionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ServiceDefinitionHandler {

    private ServiceDefinitionMapper serviceDefinitionMapper;
    private ServiceDefinitionService serviceDefinitionService;
    private PaginationMapper paginationMapper;

    public ResponseEntity<?> getById(Long id) {
        ServiceDefinition serviceDefinition = serviceDefinitionService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ServiceDefinition.class.getSimpleName(),id));
        ServiceDefinitionDto serviceDefinitionDto = serviceDefinitionMapper.toDto(serviceDefinition);
        return ResponseEntity.ok(serviceDefinitionDto);
    }

    public ResponseEntity<?> getAll(Integer pageNo, Integer pageSize) {
        Page<ServiceDefinition> serviceDefinitions = serviceDefinitionService.getAll(pageNo, pageSize);
        List<ServiceDefinitionDto> content = serviceDefinitionMapper.toDto(serviceDefinitions.getContent());
        PaginationReultDto paginatedResult = new PaginationReultDto();
        paginatedResult.setData(content);
        paginatedResult.setPagination(paginationMapper.toPaginationDto(serviceDefinitions));
        return ResponseEntity.ok(paginatedResult);
    }

}
