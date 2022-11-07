package com.example.proxy.rest.handler;

import com.example.proxy.entity.ServiceType;
import com.example.proxy.rest.dto.ServiceTypeDto;
import com.example.proxy.rest.dto.common.PaginationReultDto;
import com.example.proxy.rest.entitymapper.common.PaginationMapper;
import com.example.proxy.rest.entitymapper.ServiceTypeMapper;
import com.example.proxy.rest.exception.ResourceNotFoundException;
import com.example.proxy.service.ServiceTypeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ServiceTypeHandler {

    private ServiceTypeMapper serviceTypeMapper;
    private ServiceTypeService serviceTypeService;
    private PaginationMapper paginationMapper;

    public ResponseEntity<?> getById(Long id) {
        ServiceType serviceType = serviceTypeService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ServiceType.class.getSimpleName(),id));
        ServiceTypeDto serviceTypeDto = serviceTypeMapper.toDto(serviceType);
        return ResponseEntity.ok(serviceTypeDto);
    }

    public ResponseEntity<?> getAll(Long id,Integer pageNo, Integer pageSize){
        Page<ServiceType> serviceTypes = serviceTypeService.getAll(pageNo, pageSize);
        List<ServiceTypeDto> content= serviceTypeMapper.toDto(serviceTypes.getContent());
        PaginationReultDto paginatedResult = new PaginationReultDto();
        paginatedResult.setData(content);
        paginatedResult.setPagination(paginationMapper.toPaginationDto(serviceTypes));
        return ResponseEntity.ok(paginatedResult);
    }

    public ResponseEntity<?> getAllByService(Long serviceId, Integer pageNo, Integer pageSize){
        Page<ServiceType> serviceTypes = serviceTypeService.getByService(serviceId,pageNo, pageSize);
        List<ServiceTypeDto> content= serviceTypeMapper.toDto(serviceTypes.getContent());
        PaginationReultDto paginatedResult = new PaginationReultDto();
        paginatedResult.setData(content);
        paginatedResult.setPagination(paginationMapper.toPaginationDto(serviceTypes));
        return ResponseEntity.ok(paginatedResult);
    }

}
