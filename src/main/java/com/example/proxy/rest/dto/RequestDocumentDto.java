package com.example.proxy.rest.dto;

import com.example.proxy.rest.dto.common.RestDto;
import lombok.Data;

@Data
public class RequestDocumentDto extends RestDto {

    private String name;

    private String type;

    private byte[] file;

    private String description;

    private DocumentTypeDto documentType;

    private RequestDto request;

}
