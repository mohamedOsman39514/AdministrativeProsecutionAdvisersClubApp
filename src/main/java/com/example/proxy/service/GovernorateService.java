package com.example.proxy.service;

import com.example.proxy.entity.Governorate;
import com.example.proxy.repository.GovernorateRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GovernorateService {

    private GovernorateRepository governorateRepository;

    public Optional<Governorate> getById(Long id) {
        return governorateRepository.findById(id);
    }

    public Page<Governorate> getAll(Integer page, Integer size) {
        return governorateRepository.findAll(PageRequest.of(page, size));
    }

}
