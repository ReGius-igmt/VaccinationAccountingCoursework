package ru.regiuss.server.model;

import com.fasterxml.jackson.annotation.JsonView;
import ru.regiuss.server.Views;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

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
    @JsonView(Views.Simple.class)
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

    @Lob
    @Column(name = "comment")
    @JsonView(Views.Full.class)
    private String comment;

    @ManyToMany
    @JsonView(Views.Full.class)
    @JoinTable(
            name = "receptions_medicines",
            joinColumns = {@JoinColumn(name = "reception_id")},
            inverseJoinColumns = {@JoinColumn(name = "medicine_id")}
    )
    private Set<Medicine> medicines;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public Set<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(Set<Medicine> medicines) {
        this.medicines = medicines;
    }
}