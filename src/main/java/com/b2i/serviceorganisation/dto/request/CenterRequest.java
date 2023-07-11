package com.b2i.serviceorganisation.dto.request;

public class CenterRequest {

    private Long id;

    private String name;

    private String reference;

    private String observation;

    public CenterRequest() {

    }

    public CenterRequest(String name, String reference) {
        this.name = name;
        this.reference = reference;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    @Override
    public String toString() {
        return "CenterRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", reference='" + reference + '\'' +
                ", observation='" + observation + '\'' +
                '}';
    }
}
