package com.example.proxy.rest.controller;

import com.example.proxy.rest.dto.DocumentTypeDto;
import com.example.proxy.rest.handler.DocumentTypeHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/document_type")
@Tag(name = "Document Type", description = "Rest Api For Document Type")
public class DocumentTypeController {

    private DocumentTypeHandler documentTypeHandler;

    @GetMapping
    @Operation(summary = "get all Document Types")
    public ResponseEntity<?> getAll(@RequestParam(value = "page") Integer pageNo,
                                    @RequestParam(value = "size") Integer pageSize) {
        return documentTypeHandler.getAll(pageNo, pageSize);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get Document Type By Id")
    public ResponseEntity<?> getById(@PathVariable(value = "id") Long id) {
        return documentTypeHandler.getById(id);
    }

    @PostMapping
    @Operation(summary = "create new Document Type")
    public ResponseEntity<?> save(@Valid @RequestBody DocumentTypeDto documentTypeDto) {
        return documentTypeHandler.save(documentTypeDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update details for Document Type")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody DocumentTypeDto documentTypeDto) {
        return documentTypeHandler.update(id, documentTypeDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete Document Type By Id")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return documentTypeHandler.deleteById(id);
    }

}
