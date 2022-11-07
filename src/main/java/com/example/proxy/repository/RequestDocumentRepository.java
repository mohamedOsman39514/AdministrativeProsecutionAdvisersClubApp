package com.example.proxy.repository;

import com.example.proxy.entity.RequestDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RequestDocumentRepository extends JpaRepository<RequestDocument, Long> {

    @Query(value = "SELECT d FROM RequestDocument d WHERE d.description LIKE %:description%")
    Page<RequestDocument> findByDescription(@Param("description") String description, Pageable pageable);

}

