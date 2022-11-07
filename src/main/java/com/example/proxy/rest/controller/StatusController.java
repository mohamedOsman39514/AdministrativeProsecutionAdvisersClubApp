package com.example.proxy.rest.controller;

import com.example.proxy.rest.handler.StatusHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/status")
@Tag(name = "Status", description = "Rest Api For Status")
public class StatusController {

    private StatusHandler statusHandler;

    @GetMapping
    @Operation(summary = "get all statuses")
    public ResponseEntity<?> getAll(@RequestParam(value = "page") Integer pageNo,
                                    @RequestParam(value = "size") Integer pageSize) {
        return statusHandler.getAll(pageNo, pageSize);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get status By Id")
    public ResponseEntity<?> getById(@PathVariable(value = "id") Long id) {
        return statusHandler.getById(id);
    }

}
