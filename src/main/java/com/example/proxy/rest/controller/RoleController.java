package com.example.proxy.rest.controller;

import com.example.proxy.rest.handler.RoleHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/role")
@Tag(name = "Role", description = "Rest Api For Roles")
public class RoleController {

    private RoleHandler roleHandler;

    @GetMapping("/{id}")
    @Operation(summary = "get role By Id")
    public ResponseEntity<?> getById(@PathVariable(value = "id") Long id) {
        return roleHandler.getById(id);
    }

    @GetMapping
    @Operation(summary = "get all roles")
    public ResponseEntity<?> getAll(@RequestParam(value = "page") Integer pageNo,
                                    @RequestParam(value = "size") Integer pageSize) {
        return roleHandler.getAll(pageNo, pageSize);
    }

}
