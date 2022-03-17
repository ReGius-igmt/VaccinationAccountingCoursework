package ru.regiuss.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.Date;

public class Reception {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("user")
    private User user;
    @JsonProperty("service")
    private Service service;
    @JsonProperty("status")
    private Integer status;
    @JsonProperty("date")
    private Date date;
    @JsonProperty("medicine")
    private Medicine medicine;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Reception{" +
                "id=" + id +
                ", user=" + user +
                ", service=" + service +
                ", status=" + status +
                ", date=" + date +
                ", medicine=" + medicine +
                '}';
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

}