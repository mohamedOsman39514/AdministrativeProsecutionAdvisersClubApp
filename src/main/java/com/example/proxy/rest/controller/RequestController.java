package com.example.proxy.rest.controller;

import com.example.proxy.rest.dto.RequestDto;
import com.example.proxy.rest.handler.RequestHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/request")
@Tag(name = "Request", description = "Rest Api For Request")
public class RequestController {

    private RequestHandler requestHandler;

    @GetMapping
    @Operation(summary = "get all requests")
    public ResponseEntity<?> getAll(@RequestParam(value = "statusId", required = false) Long statusId,
                                    @RequestParam(value = "userId", required = false) Long userId,
                                    @RequestParam(value = "page") Integer pageNo,
                                    @RequestParam(value = "size") Integer pageSize) {
        return requestHandler.getAll(statusId, userId, pageNo, pageSize);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get request By Id")
    public ResponseEntity<?> getById(@PathVariable(value = "id") Long id) {
        return requestHandler.getById(id);
    }

    @PostMapping
    @Operation(summary = "create new request")
    public ResponseEntity<?> create(@Valid @RequestBody RequestDto requestDto) {
        return requestHandler.create(requestDto);
    }

    @PutMapping("request/{requestId}/status/{statusId}")
    @Operation(summary = "update status for request")
    public ResponseEntity<?> updateRequestStatus(@PathVariable(value = "requestId") Long requestId,
                                                 @PathVariable(value = "statusId") Long statusId) {
        return requestHandler.updateRequestStatus(requestId, statusId);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete request By Id")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return requestHandler.delete(id);
    }

}
