package com.example.proxy.rest.controller;

import com.example.proxy.rest.dto.PartyDto;
import com.example.proxy.rest.handler.PartyHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/party")
@Tag(name = "Party", description = "Rest Api For Party")
public class PartyController {

    private PartyHandler partyHandler;

    @GetMapping
    @Operation(summary = "get all parties")
    public ResponseEntity<?> getAll(@RequestParam(value = "page") Integer pageNo,
                                    @RequestParam(value = "size") Integer pageSize) {
        return partyHandler.getAll(pageNo, pageSize);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get party By Id")
    public ResponseEntity<?> getById(@PathVariable(value = "id") Long id) {
        return partyHandler.getById(id);
    }

    @PostMapping
    @Operation(summary = "create new Party")
    public ResponseEntity<?> save(@Valid @RequestBody PartyDto partyDto) {
        return partyHandler.save(partyDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update details for Party")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody PartyDto partyDto) {
        return partyHandler.update(id, partyDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete Party By Id")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return partyHandler.deleteById(id);
    }

}
