package com.example.proxy.rest.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationDto {

    private Integer pageNo;

    private Integer pageSize;

    private Long totalElements;

    private Integer totalPages;

}
