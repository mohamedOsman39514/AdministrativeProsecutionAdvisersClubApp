package com.example.proxy.rest.dto.common;

import lombok.Data;

import java.util.List;


@Data
public class PaginationReultDto {

    private List<?> data;
    private PaginationDto pagination;

}
