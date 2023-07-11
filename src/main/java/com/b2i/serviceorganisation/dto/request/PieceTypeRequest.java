package com.b2i.serviceorganisation.dto.request;

public class PieceTypeRequest {

    private Long id;

    private String label;

    public PieceTypeRequest(){

    }

    public PieceTypeRequest(String label) {
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
        return "PieceTypeRequest{" +
                "id=" + id +
                ", label='" + label + '\'' +
                '}';
    }
}
