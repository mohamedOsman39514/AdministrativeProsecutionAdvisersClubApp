package com.example.proxy.service;

import com.example.proxy.entity.ServiceDefinition;
import com.example.proxy.repository.ServiceDefinitionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ServiceDefinitionService {

    private ServiceDefinitionRepository serviceDefinitionRepository;

    public Optional<ServiceDefinition> getById(Long id) {
        return serviceDefinitionRepository.findById(id);
    }

    public Page<ServiceDefinition> getAll(Integer page, Integer size) {
        return serviceDefinitionRepository.findAll(PageRequest.of(page, size));
    }

}
