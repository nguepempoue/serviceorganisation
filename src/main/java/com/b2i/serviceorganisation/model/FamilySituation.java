package com.b2i.serviceorganisation.model;

import javax.persistence.*;

@Entity
@Table(name = "family_situation")
public class FamilySituation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;

    public FamilySituation(String label) {
        this.label = label;
    }

    public FamilySituation() {

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
        return "FamilySituation{" +
                "id=" + id +
                ", label='" + label + '\'' +
                '}';
    }
}
