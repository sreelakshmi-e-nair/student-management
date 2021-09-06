package com.example.demo.StudentManagement.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "StudentData")
public class StudentModel {

    @Id
    private int id;
    private String firstName;
    private String lastName;
    private Date dob;
    private Address permanentAddress;
    private Address communicationAddress;
    private String mobile_number;
    private String email;
    private String fatherName;
    private String fatherMobile;
    private String guardian;
    private String guardianMobile;
    private Academics academicsList;


    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Address getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(Address permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public Address getCommunicationAddress() {
        return communicationAddress;
    }

    public void setCommunicationAddress(Address communicationAddress) {
        this.communicationAddress = communicationAddress;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFatherMobile() {
        return fatherMobile;
    }

    public void setFatherMobile(String fatherMobile) {
        this.fatherMobile = fatherMobile;
    }

    public String getGuardian() {
        return guardian;
    }

    public void setGuardian(String guardian) {
        this.guardian = guardian;
    }

    public String getGuardianMobile() {
        return guardianMobile;
    }

    public void setGuardianMobile(String guardianMobile) {
        this.guardianMobile = guardianMobile;
    }

    public Academics getAcademicsList() {
        return academicsList;
    }

    public void setAcademicsList(Academics academicsList) {
        this.academicsList = academicsList;
    }

    @Override
    public String toString() {
        return "StudentModel{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", permanentAddress=" + permanentAddress +
                ", communicationAddress=" + communicationAddress +
                ", mobile_number='" + mobile_number + '\'' +
                ", email='" + email + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", fatherMobile='" + fatherMobile + '\'' +
                ", guardian='" + guardian + '\'' +
                ", guardianMobile='" + guardianMobile + '\'' +
                ", academicsList=" + academicsList +
                '}';
    }
}
