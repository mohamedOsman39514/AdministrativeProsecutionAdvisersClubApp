package com.example.proxy.rest.handler;

import com.example.proxy.entity.Status;
import com.example.proxy.rest.dto.StatusDto;
import com.example.proxy.rest.dto.common.PaginationReultDto;
import com.example.proxy.rest.entitymapper.common.PaginationMapper;
import com.example.proxy.rest.entitymapper.StatusMapper;
import com.example.proxy.rest.exception.ResourceNotFoundException;
import com.example.proxy.service.StatusService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class StatusHandler {

    private StatusMapper statusMapper;
    private StatusService statusService;
    private PaginationMapper paginationMapper;

    public ResponseEntity<?> getById(Long id) {
        Status status = statusService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Status.class.getSimpleName(),id));
        StatusDto statusDto = statusMapper.toDto(status);
        return ResponseEntity.ok(statusDto);
    }

    public ResponseEntity<?> getAll(Integer pageNo, Integer pageSize){
        Page<Status> statuses = statusService.getAll(pageNo, pageSize);
        List<StatusDto> content= statusMapper.toDto(statuses.getContent());
        PaginationReultDto paginatedResult = new PaginationReultDto();
        paginatedResult.setData(content);
        paginatedResult.setPagination(paginationMapper.toPaginationDto(statuses));
        return ResponseEntity.ok(paginatedResult);
    }

}
