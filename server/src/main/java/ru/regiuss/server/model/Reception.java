package ru.regiuss.server.model;

import com.fasterxml.jackson.annotation.JsonView;
import ru.regiuss.server.Views;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "receptions")
public class Reception {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JsonView(Views.Simple.class)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonView(Views.Simple.class)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "laboratory_id", nullable = false)
    @JsonView(Views.Full.class)
    private Laboratory laboratory;

    @ManyToOne(optional = false)
    @JoinColumn(name = "service_id", nullable = false)
    @JsonView(Views.Simple.class)
    private Service service;

    @Column(name = "status", nullable = false)
    @JsonView(Views.Simple.class)
    private Integer status;

    @Column(name = "date", nullable = false)
    @JsonView(Views.Simple.class)
    private Instant date;

    @ManyToOne
    @JoinColumn(name = "medicine_id")
    @JsonView(Views.Simple.class)
    private Medicine medicine;

    public Medicine getMedicine() {
        return medicine;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
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

    public Laboratory getLaboratory() {
        return laboratory;
    }

    public void setLaboratory(Laboratory laboratory) {
        this.laboratory = laboratory;
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

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    @Override
    public String toString() {
        return "Reception{" +
                "id=" + id +
                ", user=" + user +
                ", laboratory=" + laboratory +
                ", service=" + service +
                ", status=" + status +
                ", date=" + date +
                ", medicine=" + medicine +
                '}';
    }
}