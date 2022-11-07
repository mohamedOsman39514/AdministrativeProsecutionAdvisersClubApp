package com.example.proxy.rest.controller;

import com.example.proxy.rest.handler.ServiceDefinitionHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/service_definition")
@Tag(name = "Service Definition", description = "Rest Api For Service Definition")
public class ServiceDefinitionController {

    private ServiceDefinitionHandler serviceDefinitionHandler;

    @GetMapping
    @Operation(summary = "get all service definition")
    public ResponseEntity<?> getAll(@RequestParam(value = "page", defaultValue = "0") Integer pageNo,
                                    @RequestParam(value = "size") Integer pageSize) {
        return serviceDefinitionHandler.getAll(pageNo, pageSize);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get service definition By Id")
    public ResponseEntity<?> getById(@PathVariable(value = "id") Long id) {
        return serviceDefinitionHandler.getById(id);
    }

}

