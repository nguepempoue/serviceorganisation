package com.b2i.serviceorganisation.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="area")
@Data
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String reference;

    @Column(columnDefinition = "text")
    private String observation;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Club> clubs;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<Post> posts = new ArrayList<>();

    public Area() {

    }

    public Area(String name, String reference, List<Club> clubs) {
        this.name = name;
        this.reference = reference;
        this.clubs = clubs;

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

    public List<Club> getClubs() {
        return clubs;
    }

    public void setClubs(List<Club> clubs) {
        this.clubs = clubs;
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
        return "Area{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", reference='" + reference + '\'' +
                ", observation='" + observation + '\'' +
                ", clubs=" + clubs +
                '}';
    }
}
