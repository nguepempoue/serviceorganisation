package com.b2i.serviceorganisation.dto.request;

public class MainOfficeRequest {

    private Long id;

    private String name;

    public MainOfficeRequest() {

    }

    public MainOfficeRequest(Long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "MainOfficeRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
