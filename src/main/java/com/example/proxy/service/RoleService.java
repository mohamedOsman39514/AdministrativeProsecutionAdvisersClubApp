package com.example.proxy.service;

import com.example.proxy.entity.Role;
import com.example.proxy.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleService {

    private RoleRepository roleRepository;

    public Optional<Role> getById(Long id) {
        return roleRepository.findById(id);
    }

    public Page<Role> getAll(Integer page, Integer size) {
        return roleRepository.findAll(PageRequest.of(page, size));
    }

}
