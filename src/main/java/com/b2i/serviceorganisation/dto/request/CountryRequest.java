package com.b2i.serviceorganisation.dto.request;

public class CountryRequest {

    private Long id;

    private String name;

    public CountryRequest() {

    }

    public CountryRequest(String name) {
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
        return "CountryRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
