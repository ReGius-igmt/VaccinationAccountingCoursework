package ru.regiuss.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
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
    @JsonProperty("comment")
    private String comment;
    @JsonProperty("medicines")
    private Set<Medicine> medicines;

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

    public Set<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(Set<Medicine> medicines) {
        this.medicines = medicines;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Reception{" +
                "id=" + id +
                ", user=" + user +
                ", service=" + service +
                ", status=" + status +
                ", date=" + date +
                '}';
    }

}