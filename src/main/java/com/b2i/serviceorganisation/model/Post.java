package com.b2i.serviceorganisation.model;

import com.b2i.serviceorganisation.constant.OrganisationLevelEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Integer maxNumberOfUser;

    @Column(length = 4096)
    private String description;

    private OrganisationLevelEnum organisationLevelEnum;

    @OneToMany
    private List<User> operators;

    @ManyToOne
    @JsonIgnore
    private MainOffice mainOffice;

    @ManyToOne
    @JsonIgnore
    private Center center;

    @ManyToOne
    @JsonIgnore
    private Area area;

    @ManyToOne
    @JsonIgnore
    private Club club;
}
