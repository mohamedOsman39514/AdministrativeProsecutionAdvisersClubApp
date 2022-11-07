package com.example.proxy.service;

import com.example.proxy.entity.ServiceType;
import com.example.proxy.repository.ServiceTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ServiceTypeService {

    private ServiceTypeRepository serviceTypeRepository;

    public Optional<ServiceType> getById(Long id) {
        return serviceTypeRepository.findById(id);
    }

    public Page<ServiceType> getAll(Integer page, Integer size) {
        return serviceTypeRepository.findAll(PageRequest.of(page, size));
    }

    public Page<ServiceType> getByService(Long serviceId, Integer page, Integer size) {
        return serviceTypeRepository.findByService(serviceId,PageRequest.of(page, size));
    }

}
