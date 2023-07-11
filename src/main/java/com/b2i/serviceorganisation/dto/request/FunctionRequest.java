package com.b2i.serviceorganisation.dto.request;

import lombok.Data;

@Data
public class FunctionRequest {
    private Long id;
    private String name;

    public FunctionRequest(String name) {
        this.name = name;
    }
}
