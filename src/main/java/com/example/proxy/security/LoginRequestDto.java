package com.example.proxy.security;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class LoginRequestDto {

    @NotBlank(message = "username is mandatory")
    @Size(max = 100, message = "username's max length allowed is 100 characters")
    private String email;

    @NotBlank(message = "password is mandatory")
    @Size(max = 100, message = "password's max length allowed is 100 characters")
    private String password;

}
