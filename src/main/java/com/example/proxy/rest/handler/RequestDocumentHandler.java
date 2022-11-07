package com.example.proxy.rest.handler;

import com.example.proxy.entity.RequestDocument;
import com.example.proxy.entity.Request;
import com.example.proxy.rest.dto.RequestDocumentDto;
import com.example.proxy.rest.dto.common.PaginationReultDto;
import com.example.proxy.rest.entitymapper.common.PaginationMapper;
import com.example.proxy.rest.exception.ResourceNotFoundException;
import com.example.proxy.rest.exception.Response;
import com.example.proxy.rest.entitymapper.RequestDocumentMapper;
import com.example.proxy.service.RequestDocumentService;
import com.example.proxy.service.RequestService;
import com.example.proxy.utils.DocumentUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Component
@AllArgsConstructor
public class RequestDocumentHandler {

    private RequestDocumentService requestDocumentService;
    private RequestDocumentMapper documentMapper;
    private RequestService requestService;
    private PaginationMapper paginationMapper;

    public ResponseEntity<?> upload(Long requestId, MultipartFile file, String description) throws IOException {
        Request request = requestService.getById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException(Request.class.getSimpleName(), requestId));
        System.out.println(request);
        RequestDocument requestDocument = new RequestDocument();
        requestDocument.setName(file.getOriginalFilename());
        requestDocument.setType(file.getContentType());
        requestDocument.setFile(DocumentUtil.compressFile(file.getBytes()));
        requestDocument.setRequest(request);
        requestDocument.setDescription(description);
        requestDocumentService.save(requestDocument);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response("Document uploaded successfully: " + requestDocument.getName()));
    }

    public ResponseEntity<?> update(Long documentID, MultipartFile file, String description) throws IOException {
        RequestDocument requestDocument = requestDocumentService.getById(documentID)
                .orElseThrow(() -> new ResourceNotFoundException(RequestDocument.class.getSimpleName(), documentID));
        requestDocument.setName(file.getOriginalFilename());
        requestDocument.setType(file.getContentType());
        requestDocument.setFile(DocumentUtil.compressFile(file.getBytes()));
        requestDocument.setDescription(description);
        requestDocumentService.save(requestDocument);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response("Document updated successfully: " + file.getOriginalFilename()));
    }

    public ResponseEntity<?> getById(Long id) throws IOException {
        RequestDocument document = requestDocumentService.getById(id).get();
        if (document == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Document Not Found");
        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(document.getType()))
                .body(DocumentUtil.decompressFile(document.getFile()));
    }

    public ResponseEntity<?> getByDescription(String description, Integer pageNo, Integer pageSize) {
        Page<RequestDocument> requestDocuments = requestDocumentService.getDocumentByDescription(description, pageNo, pageSize);
        List<RequestDocumentDto> content = documentMapper.toDto(requestDocuments.getContent());
        PaginationReultDto paginatedResult = new PaginationReultDto();
        paginatedResult.setData(content);
        paginatedResult.setPagination(paginationMapper.toPaginationDto(requestDocuments));
        return ResponseEntity.ok(paginatedResult);
    }

    public ResponseEntity<?> delete(Long id) {
        requestDocumentService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RequestDocument.class.getSimpleName(), id));
        requestDocumentService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response("deleted"));
    }

}
