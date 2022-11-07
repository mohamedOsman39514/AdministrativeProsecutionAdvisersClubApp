package com.example.proxy.rest.handler;

import com.example.proxy.entity.DocumentType;
import com.example.proxy.entity.Request;
import com.example.proxy.rest.dto.DocumentTypeDto;
import com.example.proxy.rest.dto.common.PaginationReultDto;
import com.example.proxy.rest.entitymapper.DocumentTypeMapper;
import com.example.proxy.rest.entitymapper.common.PaginationMapper;
import com.example.proxy.rest.exception.*;
import com.example.proxy.service.DocumentTypeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DocumentTypeHandler {

    private DocumentTypeService documentTypeService;
    private DocumentTypeMapper documentTypeMapper;
    private PaginationMapper paginationMapper;

    public ResponseEntity<?> save(DocumentTypeDto documentTypeDto) {
        DocumentType documentType = documentTypeMapper.toEntity(documentTypeDto);
            documentTypeService.save(documentType);
            return ResponseEntity.status(HttpStatus.CREATED).body(documentTypeMapper.toDto(documentType));
    }

    public ResponseEntity<?> getById(Long id) {
        DocumentType documentType = documentTypeService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(DocumentType.class.getSimpleName(),id));
        DocumentTypeDto documentTypeDto = documentTypeMapper.toDto(documentType);
        return ResponseEntity.ok(documentTypeDto);
    }

    public ResponseEntity<?> getAll(Integer pageNo, Integer pageSize){
        Page<DocumentType> documentTypes = documentTypeService.getAll(pageNo, pageSize);
        List<DocumentTypeDto> content= documentTypeMapper.toDto(documentTypes.getContent());
        PaginationReultDto paginatedResult = new PaginationReultDto();
        paginatedResult.setData(content);
        paginatedResult.setPagination(paginationMapper.toPaginationDto(documentTypes));
        return ResponseEntity.ok(paginatedResult);
    }

    public ResponseEntity<?> update(Long id, DocumentTypeDto dto) {
        documentTypeService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(DocumentType.class.getSimpleName(), id));
        DocumentType documentType = documentTypeMapper.toEntity(dto);
        documentTypeService.save(documentType);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> deleteById(Long id) {
        documentTypeService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Request.class.getSimpleName(),id));
        try {
            documentTypeService.deleteById(id);
        } catch (Exception exception) {
            throw new ResourceRelatedException(Request.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE.getCode());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response("deleted"));
    }

}
