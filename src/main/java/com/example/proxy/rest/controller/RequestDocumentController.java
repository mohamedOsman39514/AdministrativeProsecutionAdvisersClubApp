package com.example.proxy.rest.controller;

import com.example.proxy.rest.handler.RequestDocumentHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("/document")
@Tag(name = "Document", description = "Rest Api For Documents")
public class RequestDocumentController {

    private RequestDocumentHandler requestDocumentHandler;

    @PostMapping("/{requestId}/request")
    @Operation(summary = "upload document by request id")
    public ResponseEntity<?> uploadDocument(@PathVariable("requestId") Long requestId,
                                            @RequestParam("file") MultipartFile file,
                                            @RequestParam("description") String description) throws IOException {
        return requestDocumentHandler.upload(requestId, file, description);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update document")
    public ResponseEntity<?> updateDocument(@PathVariable Long id,
                                            @RequestParam("file") MultipartFile file,
                                            @RequestParam("description") String description) throws IOException {
        return requestDocumentHandler.update(id, file, description);
    }

    @GetMapping("/{id}")
    @Operation(summary = "find document by id")
    public ResponseEntity<?> getById(@PathVariable Long id) throws IOException {
        return requestDocumentHandler.getById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete document By Id")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return requestDocumentHandler.delete(id);
    }

    @GetMapping
    @Operation(summary = "search for files by description")
    public ResponseEntity<?> getByDescription(@RequestParam("description") String description,
                                              @RequestParam(value = "page") Integer pageNo,
                                              @RequestParam(value = "size") Integer pageSize) {
        return requestDocumentHandler.getByDescription(description, pageNo, pageSize);
    }

}
