package com.b2i.serviceorganisation.model;

import com.b2i.serviceorganisation.constant.StatusUserEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Target;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user_member")
@Data
@JsonIgnoreProperties(value = {"password"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String userName;

    private String password;

    private String phoneNumber;

    private String secondPhoneNumber = "";

    private String whatsappPhoneNumber;

    private String email;

    private String secondaryEmail = "";

    private String city;

    private String nationality;

    private String pieceId;

    private String placeOfIssue;

    private LocalDate dateOfIssue;

    private LocalDate dateOfValidity;

    private String nationalIDNumber;

    private String mainAddress;

    private String secondaryAddress = "";

    private String postalBox;

    private Long numberOfChildren;

    private StatusUserEnum statusUserEnum;

    @ManyToOne
    private MainOffice mainOffice;

    @ManyToOne
    private Center center;

    @ManyToOne
    private Area area;

    @ManyToOne
    private Club club;

    @ManyToOne
    private UserType userType;

    @ManyToOne
    private UserCategory userCategory;

    /*@ManyToOne
    private Post post;

    @ManyToOne
    private Function function;
*/
    @ManyToOne
    private Status status;

    @ManyToOne
    private Civility civility;

    @ManyToOne
    private PieceType pieceType;

    @ManyToOne
    private Country country;

    @ManyToOne
    private FamilySituation familySituation;

    //@JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Account> accounts = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY)
    @Target(User.class)
    private List<User> sponsoredUsers;

    @OneToMany
    private Set<Beneficiary> beneficiaries = new HashSet<>();


    @ManyToMany(targetEntity = Role.class, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "users_id", referencedColumnName = "id"),
            inverseJoinColumns =  @JoinColumn(name = "roles_id", referencedColumnName = "id")
    )
    private List<Role> roles = new ArrayList<>();

    public User() {

    }

    public User(Long id, String firstName, String lastName, String userName, String password, String phoneNumber, String email,
                Set<Account> accounts, List<User> sponsoredUsers,  List<Role> roles, StatusUserEnum statusUserEnum) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.accounts = accounts;
        this.sponsoredUsers = sponsoredUsers;
        this.roles = roles;
        this.statusUserEnum = statusUserEnum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public List<User> getSponsoredUsers() {
        return sponsoredUsers;
    }

    public void setSponsoredUsers(List<User> sponsoredUsers) {
        this.sponsoredUsers = sponsoredUsers;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public Civility getCivility() {
        return civility;
    }

    public void setCivility(Civility civility) {
        this.civility = civility;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<Beneficiary> getBeneficiaries() {
        return beneficiaries;
    }

    public void setBeneficiaries(Set<Beneficiary> beneficiaries) {
        this.beneficiaries = beneficiaries;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getSecondaryEmail() {
        return secondaryEmail;
    }

    public void setSecondaryEmail(String secondaryEmail) {
        this.secondaryEmail = secondaryEmail;
    }

    public String getSecondPhoneNumber() {
        return secondPhoneNumber;
    }

    public void setSecondPhoneNumber(String secondPhoneNumber) {
        this.secondPhoneNumber = secondPhoneNumber;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public void setPieceType(PieceType pieceType) {
        this.pieceType = pieceType;
    }

    public String getPieceId() {
        return pieceId;
    }

    public void setPieceId(String pieceId) {
        this.pieceId = pieceId;
    }

    public String getPlaceOfIssue() {
        return placeOfIssue;
    }

    public void setPlaceOfIssue(String placeOfIssue) {
        this.placeOfIssue = placeOfIssue;
    }

    public LocalDate getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(LocalDate dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public LocalDate getDateOfValidity() {
        return dateOfValidity;
    }

    public void setDateOfValidity(LocalDate dateOfValidity) {
        this.dateOfValidity = dateOfValidity;
    }

    public String getNationalIDNumber() {
        return nationalIDNumber;
    }

    public void setNationalIDNumber(String nationalIDNumber) {
        this.nationalIDNumber = nationalIDNumber;
    }

    public String getMainAddress() {
        return mainAddress;
    }

    public void setMainAddress(String mainAddress) {
        this.mainAddress = mainAddress;
    }

    public String getSecondaryAddress() {
        return secondaryAddress;
    }

    public void setSecondaryAddress(String secondaryAddress) {
        this.secondaryAddress = secondaryAddress;
    }

    public String getPostalBox() {
        return postalBox;
    }

    public void setPostalBox(String postalBox) {
        this.postalBox = postalBox;
    }

    public Long getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(Long numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public String getWhatsappPhoneNumber() {
        return whatsappPhoneNumber;
    }

    public void setWhatsappPhoneNumber(String whatsappPhoneNumber) {
        this.whatsappPhoneNumber = whatsappPhoneNumber;
    }

    public FamilySituation getFamilySituation() {
        return familySituation;
    }

    public void setFamilySituation(FamilySituation familySituation) {
        this.familySituation = familySituation;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public StatusUserEnum getStatusUserEnum(){
        return statusUserEnum;
    }

    public void setStatusUserEnum(StatusUserEnum statusUserEnum){
        this.statusUserEnum = statusUserEnum;
    }

/*
    public Function getFunction() {
        return function;
    }

    public void setPost(Post post) {
        this.function = function;
    }
*/

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", secondPhoneNumber='" + secondPhoneNumber + '\'' +
                ", whatsappPhoneNumber='" + whatsappPhoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", secondaryEmail='" + secondaryEmail + '\'' +
                ", city='" + city + '\'' +
                ", nationality='" + nationality + '\'' +
                ", pieceId='" + pieceId + '\'' +
                ", placeOfIssue='" + placeOfIssue + '\'' +
                ", dateOfIssue=" + dateOfIssue +
                ", dateOfValidity=" + dateOfValidity +
                ", nationalIDNumber='" + nationalIDNumber + '\'' +
                ", mainAddress='" + mainAddress + '\'' +
                ", secondaryAddress='" + secondaryAddress + '\'' +
                ", postalBox='" + postalBox + '\'' +
                ", numberOfChildren=" + numberOfChildren +
                ", status=" + status +
                ", civility=" + civility +
                ", pieceType=" + pieceType +
                ", country=" + country +
                ", familySituation=" + familySituation +
               // ", function=" + function +
                ", accounts=" + accounts +
                ", sponsoredUsers=" + sponsoredUsers +
                ", beneficiaries=" + beneficiaries +
                ", roles=" + roles +
                '}';
    }
}
