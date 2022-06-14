package ru.regiuss.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Laboratory {
    private Integer id;
    private String city;
    private String street;
    private String dNum;
    private String displayName;

    public Laboratory() {}

    public Laboratory(Integer id, String city, String displayName) {
        this.id = id;
        this.city = city;
        this.displayName = displayName;
    }

    public String getDNum() {
        return dNum;
    }

    public void setDNum(String dNum) {
        this.dNum = dNum;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getdNum() {
        return dNum;
    }

    public void setdNum(String dNum) {
        this.dNum = dNum;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}