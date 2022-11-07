package com.example.proxy.rest.controller;

import com.example.proxy.rest.handler.ServiceTypeHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/service_type")
@Tag(name = "Service Type", description = "Rest Api For Service Type")
public class ServiceTypeController {

    private ServiceTypeHandler serviceTypeHandler;

    @GetMapping("/service/{id}")
    @Operation(summary = "get all service types by service id")
    public ResponseEntity<?> getAllByService(@PathVariable(value = "id") Long id,
                                             @RequestParam(value = "page") Integer pageNo,
                                             @RequestParam(value = "size") Integer pageSize) {
        return serviceTypeHandler.getAllByService(id, pageNo, pageSize);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get service type by id")
    public ResponseEntity<?> getById(@PathVariable(value = "id") Long id) {
        return serviceTypeHandler.getById(id);
    }

}
