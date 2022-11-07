package com.example.proxy.service;

import com.example.proxy.entity.DocumentType;
import com.example.proxy.entity.Job;
import com.example.proxy.repository.DocumentTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DocumentTypeService {

    private DocumentTypeRepository documentTypeRepository;

    public Optional<DocumentType> getById(Long id) {
        return documentTypeRepository.findById(id);
    }

    public Page<DocumentType> getAll(Integer page, Integer size) {
        return documentTypeRepository.findAll(PageRequest.of(page, size));
    }

    public DocumentType save(DocumentType documentType) {
        return documentTypeRepository.save(documentType);
    }

    public void deleteById(Long id) {
        documentTypeRepository.deleteById(id);
    }

}
