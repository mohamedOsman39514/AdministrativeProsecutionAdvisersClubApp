package com.example.proxy.repository;

import com.example.proxy.entity.ServiceDefinition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceDefinitionRepository extends JpaRepository<ServiceDefinition, Long> {
}
