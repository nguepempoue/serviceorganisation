package com.b2i.serviceorganisation.dto.request;

public class StatusRequest {

    private Long id;

    private String label;

    public StatusRequest() {

    }

    public StatusRequest(String label) {
        this.label = label;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "StatusRequest{" +
                "id=" + id +
                ", label='" + label + '\'' +
                '}';
    }
}
