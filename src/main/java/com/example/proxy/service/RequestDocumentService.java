package com.example.proxy.service;

import com.example.proxy.entity.RequestDocument;
import com.example.proxy.repository.RequestDocumentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RequestDocumentService {

    private RequestDocumentRepository requestDocumentRepository;

    public RequestDocument save(RequestDocument requestDocument) {
        return requestDocumentRepository.save(requestDocument);
    }

    public Page<RequestDocument> getDocumentByDescription(String description, Integer page, Integer size) {
        return requestDocumentRepository.findByDescription(description, PageRequest.of(page, size));
    }

    public Optional<RequestDocument> getById(Long id) {
        return requestDocumentRepository.findById(id);
    }

    public Page<RequestDocument> getAll(Integer page, Integer size) {
        return requestDocumentRepository.findAll(PageRequest.of(page, size));
    }

    public void deleteById(Long id) {
        requestDocumentRepository.deleteById(id);
    }

}
