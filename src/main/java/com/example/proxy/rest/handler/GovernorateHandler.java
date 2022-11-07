package com.example.proxy.rest.handler;

import com.example.proxy.entity.Governorate;
import com.example.proxy.rest.dto.GovernorateDto;
import com.example.proxy.rest.dto.common.PaginationReultDto;
import com.example.proxy.rest.entitymapper.common.PaginationMapper;
import com.example.proxy.rest.entitymapper.GovernorateMapper;
import com.example.proxy.rest.exception.ResourceNotFoundException;
import com.example.proxy.service.GovernorateService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class GovernorateHandler {

    private GovernorateService governorateService;
    private GovernorateMapper governorateMapper;
    private PaginationMapper paginationMapper;

    public ResponseEntity<?> getById(Long id) {
        Governorate governorate = governorateService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Governorate.class.getSimpleName(),id));
        GovernorateDto governorateDto = governorateMapper.toDto(governorate);
        return ResponseEntity.ok(governorateDto);
    }

    public ResponseEntity<?> getAll(Integer pageNo, Integer pageSize) {
        Page<Governorate> governorates = governorateService.getAll(pageNo, pageSize);
        List<GovernorateDto> content = governorateMapper.toDto(governorates.getContent());
        PaginationReultDto paginatedResult = new PaginationReultDto();
        paginatedResult.setData(content);
        paginatedResult.setPagination(paginationMapper.toPaginationDto(governorates));
        return ResponseEntity.ok(paginatedResult);
    }

}
