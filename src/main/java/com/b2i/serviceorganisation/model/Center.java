package com.b2i.serviceorganisation.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "center")
@Data
public class Center {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String reference;

    @Column(columnDefinition = "text")
    private String observation;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Area> areas = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<Post> posts = new ArrayList<>();

    public Center() {

    }

    public Center(Long id, String name, List<Area> areas){
        this.id = id;
        this.name = name;
        this.areas = areas;
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

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
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
        return "Center{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", reference='" + reference + '\'' +
                ", observation='" + observation + '\'' +
                ", areas=" + areas +
                '}';
    }
}
