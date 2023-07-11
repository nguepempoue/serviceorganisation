package com.b2i.serviceorganisation.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "main_office")
@Data
public class MainOffice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text")
    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Center> centers;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<Post> posts = new ArrayList<>();

/*    @OneToMany(fetch = FetchType.LAZY)
    private List<User> centersGeneralAssembly;

    @OneToMany(fetch = FetchType.LAZY)
    private List<User> executiveBoard;

    @OneToMany(fetch = FetchType.LAZY)
    private List<User> governanceAndCompensationCommittee;

    @OneToMany(fetch = FetchType.LAZY)
    private List<User> strategicDevelopmentCommittee;

    @OneToMany(fetch = FetchType.LAZY)
    private List<User> productionAndMonitoringCommittee;*/

/*    public Boolean inCentersGeneralAssembly(User user) {
        return centersGeneralAssembly.contains(user);
    }

    public Boolean inExecutiveBoard(User user) {
        return executiveBoard.contains(user);
    }

    public Boolean inGovernanceAndCompensationCommittee(User user) {
        return governanceAndCompensationCommittee.contains(user);
    }

    public Boolean inStrategicDevelopmentCommittee(User user) {
        return strategicDevelopmentCommittee.contains(user);
    }

    public Boolean inProductionAndMonitoringCommittee(User user) {
        return productionAndMonitoringCommittee.contains(user);
    }*/

    public MainOffice() {

    }

    public MainOffice(Long id, String name, List<Center> centers) {
        this.id = id;
        this.name = name;
        this.centers = centers;
       /* this.centersGeneralAssembly = centersGeneralAssembly;
        this.executiveBoard = executiveBoard;
        this.governanceAndCompensationCommittee = governanceAndCompensationCommittee;
        this.strategicDevelopmentCommittee = strategicDevelopmentCommittee;
        this.productionAndMonitoringCommittee = productionAndMonitoringCommittee;*/
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

    public List<Center> getCenters() {
        return centers;
    }

    public void setCenters(List<Center> centers) {
        this.centers = centers;
    }

/*    public List<User> getCentersGeneralAssembly() {
        return centersGeneralAssembly;
    }

    public void setCentersGeneralAssembly(List<User> centersGeneralAssembly) {
        this.centersGeneralAssembly = centersGeneralAssembly;
    }

    public List<User> getExecutiveBoard() {
        return executiveBoard;
    }

    public void setExecutiveBoard(List<User> executiveBoard) {
        this.executiveBoard = executiveBoard;
    }

    public List<User> getGovernanceAndCompensationCommittee() {
        return governanceAndCompensationCommittee;
    }

    public void setGovernanceAndCompensationCommittee(List<User> governanceAndCompensationCommittee) {
        this.governanceAndCompensationCommittee = governanceAndCompensationCommittee;
    }

    public List<User> getStrategicDevelopmentCommittee() {
        return strategicDevelopmentCommittee;
    }

    public void setStrategicDevelopmentCommittee(List<User> strategicDevelopmentCommittee) {
        this.strategicDevelopmentCommittee = strategicDevelopmentCommittee;
    }

    public List<User> getProductionAndMonitoringCommittee() {
        return productionAndMonitoringCommittee;
    }

    public void setProductionAndMonitoringCommittee(List<User> productionAndMonitoringCommittee) {
        this.productionAndMonitoringCommittee = productionAndMonitoringCommittee;
    }*/

    @Override
    public String toString() {
        return "MainOffice{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", centers=" + centers +
               /* ", centersGeneralAssembly=" + centersGeneralAssembly +
                ", executiveBoard=" + executiveBoard +
                ", governanceAndCompensationCommittee=" + governanceAndCompensationCommittee +
                ", strategicDevelopmentCommittee=" + strategicDevelopmentCommittee +
                ", productionAndMonitoringCommittee=" + productionAndMonitoringCommittee +*/
                '}';
    }
}
