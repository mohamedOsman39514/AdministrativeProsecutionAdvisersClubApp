package com.example.proxy.security;

import com.example.proxy.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@AllArgsConstructor
@RestController
@Tag(name = "Authentication", description = "Rest Api For Authentication ")
public class AuthController {

    private AuthHandler authHandler;


    @PostMapping("/login")
    @Operation(summary = "user login")
    public ResponseEntity<?> createToken(@Valid @RequestBody LoginRequestDto user) throws Exception {
        return authHandler.createToken(user);
    }

    @GetMapping(value = "/refresh")
    @Operation(summary = "refresh token")
    public ResponseEntity<?> refreshToken(HttpServletRequest request) throws Exception {
        return authHandler.refreshtoken(request);

    }

}