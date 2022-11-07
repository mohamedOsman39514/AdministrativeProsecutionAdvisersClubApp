package com.example.proxy.rest.dto.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Data
@EqualsAndHashCode(of = {"name"}, callSuper = true)
@ToString(of = {"name"}, callSuper = true)
public class LookupDto extends RestDto {

    @NotEmpty(message = "the name is required")
    private String name;

}
