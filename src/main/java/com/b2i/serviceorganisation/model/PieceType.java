package com.b2i.serviceorganisation.model;

import javax.persistence.*;

@Entity
@Table(name = "piece_type")
public class PieceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;



    public PieceType(String label) {
        this.label = label;
    }

    public PieceType() {

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
        return "PieceType{" +
                "id=" + id +
                ", label='" + label + '\'' +
                '}';
    }
}
