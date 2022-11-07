package com.example.proxy.rest.dto;

import com.example.proxy.entity.Role;
import com.example.proxy.rest.dto.common.RestDto;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class UserDto extends RestDto {

    @NotEmpty(message = "please enter your name")
    private String name;

    @Email( message = "not valid email")
    @NotEmpty(message = "please enter email")
    private String username;

    @Size(min = 8, message = "password at least 8 character")
    @NotEmpty(message = "please enter password")
    private String password;

    private String phone;

    private String address;

    private String nationalId;

    private JobDto job;

    private PartyDto party;

    private GovernorateDto governorate;

    private List<Role> roles;

}
