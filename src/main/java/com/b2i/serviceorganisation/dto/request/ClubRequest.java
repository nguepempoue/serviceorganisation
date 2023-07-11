package com.b2i.serviceorganisation.dto.request;

import java.time.LocalDate;

public class ClubRequest {

    private Long id;

    private String name;

    private LocalDate creationDate;

    private String reference;

    private String observation;

    public ClubRequest() {

    }

    public ClubRequest(Long id, String name, LocalDate creationDate) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
    }

    public ClubRequest(String name, LocalDate creationDate, String reference) {
        this.name = name;
        this.creationDate = creationDate;
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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
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
        return "ClubRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creationDate=" + creationDate +
                ", reference='" + reference + '\'' +
                ", observation='" + observation + '\'' +
                '}';
    }
}
