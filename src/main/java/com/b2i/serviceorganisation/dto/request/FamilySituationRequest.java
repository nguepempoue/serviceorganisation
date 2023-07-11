package com.b2i.serviceorganisation.dto.request;

public class FamilySituationRequest {

    private Long id;

    private String label;

    public FamilySituationRequest() {

    }

    public FamilySituationRequest(String label) {
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
        return "FamilySituationRequest{" +
                "id=" + id +
                ", label='" + label + '\'' +
                '}';
    }
}
