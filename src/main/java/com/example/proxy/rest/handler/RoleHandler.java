package com.example.proxy.rest.handler;

import com.example.proxy.entity.Role;
import com.example.proxy.rest.dto.RoleDto;
import com.example.proxy.rest.dto.common.PaginationReultDto;
import com.example.proxy.rest.entitymapper.common.PaginationMapper;
import com.example.proxy.rest.entitymapper.RoleMapper;
import com.example.proxy.rest.exception.ResourceNotFoundException;
import com.example.proxy.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class RoleHandler {

    private RoleService roleService;
    private RoleMapper roleMapper;
    private PaginationMapper paginationMapper;

    public ResponseEntity<RoleDto> getById(Long id) {
        Role role = roleService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Role.class.getSimpleName(), id));
        RoleDto roleDto = roleMapper.toDto(role);
        return ResponseEntity.ok(roleDto);
    }

    public ResponseEntity<?> getAll(Integer pageNo, Integer pageSize){
        Page<Role> roles = roleService.getAll(pageNo, pageSize);
        List<RoleDto> content = roleMapper.toDto(roles.getContent());
        PaginationReultDto paginatedResult = new PaginationReultDto();
        paginatedResult.setData(content);
        paginatedResult.setPagination(paginationMapper.toPaginationDto(roles));
        return ResponseEntity.ok(paginatedResult);
    }

}
