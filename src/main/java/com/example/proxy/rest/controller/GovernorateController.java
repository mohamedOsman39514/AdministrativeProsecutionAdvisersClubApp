package com.example.proxy.rest.controller;

import com.example.proxy.rest.handler.GovernorateHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/governorate")
@Tag(name = "Governorate", description = "Rest Api For Governorate")
public class GovernorateController {

    private GovernorateHandler governorateHandler;

    @GetMapping
    @Operation(summary = "get all governorate")
    public ResponseEntity<?> getAll(@RequestParam(value = "page") Integer pageNo,
                                    @RequestParam(value = "size") Integer pageSize) {
        return governorateHandler.getAll(pageNo, pageSize);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get governorate By Id")
    public ResponseEntity<?> getById(@PathVariable(value = "id") Long id) {
        return governorateHandler.getById(id);
    }

}
