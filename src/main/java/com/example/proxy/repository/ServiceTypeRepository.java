package com.example.proxy.repository;

import com.example.proxy.entity.ServiceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ServiceTypeRepository extends JpaRepository<ServiceType, Long> {

    @Query(value = "SELECT s FROM ServiceType s WHERE s.service.id= :id")
    Page<ServiceType> findByService(@Param("id") Long id, Pageable pageable);

}
