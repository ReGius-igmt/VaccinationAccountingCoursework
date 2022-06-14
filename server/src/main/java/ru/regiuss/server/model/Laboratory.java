package ru.regiuss.server.model;

import com.fasterxml.jackson.annotation.JsonView;
import ru.regiuss.server.Views;

import javax.persistence.*;

@Entity
@Table(name = "laboratories")
public class Laboratory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JsonView(Views.Simple.class)
    private Integer id;

    @Column(name = "city", nullable = false, length = 20)
    @JsonView(Views.Simple.class)
    private String city;

    @Column(name = "street", nullable = false, length = 20)
    @JsonView(Views.Simple.class)
    private String street;

    @Column(name = "d_num", nullable = false, length = 5)
    @JsonView(Views.Simple.class)
    private String dNum;

    @Column(name = "display_name", nullable = false, length = 45)
    @JsonView(Views.Simple.class)
    private String displayName;

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