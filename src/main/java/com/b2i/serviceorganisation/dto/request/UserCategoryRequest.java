package com.b2i.serviceorganisation.dto.request;

import lombok.Data;

@Data
public class UserCategoryRequest {
    private Long id;
    private String name;
    private String description;

    public UserCategoryRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
