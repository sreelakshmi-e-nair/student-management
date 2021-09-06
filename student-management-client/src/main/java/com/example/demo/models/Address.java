package com.example.demo.models;

public class Address {
    private String house_number;
    private String house_name;
    private String street;
    private String district;
    private String state;
    private String zipcode;

    public String getHouse_number() {
        return house_number;
    }

    public void setHouse_number(String house_number) {
        this.house_number = house_number;
    }

    public String getHouse_name() {
        return house_name;
    }

    public void setHouse_name(String house_name) {
        this.house_name = house_name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "house_number='" + house_number + '\'' +
                ", house_name='" + house_name + '\'' +
                ", street='" + street + '\'' +
                ", district='" + district + '\'' +
                ", state='" + state + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }
}
