package com.b2i.serviceorganisation.model;

import javax.persistence.*;

@Entity
@Table(name = "accounting_class")
public class AccountingClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;

    private Long number;

    public AccountingClass() {

    }

    public AccountingClass(String label, Long number) {
        this.label = label;
        this.number = number;
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

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "AccountingClass{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", number=" + number +
                '}';
    }
}
