package com.b2i.serviceorganisation.dto.request;

import lombok.Data;

@Data
public class UserTypeRequest {
    private Long id;
    private String label;
    private String description;

    public UserTypeRequest(String label, String description) {
        this.label = label;
        this.description = description;
    }
}
