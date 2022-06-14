package ru.regiuss.server.model;

import com.fasterxml.jackson.annotation.JsonView;
import ru.regiuss.server.Views;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JsonView(Views.Simple.class)
    private Integer id;

    @Column(name = "name", nullable = false, length = 20)
    @JsonView(Views.Simple.class)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "medicines_services",
            joinColumns = {@JoinColumn(name = "service_id")},
            inverseJoinColumns = {@JoinColumn(name = "medicine_id")}
    )
    @JsonView(Views.Simple.class)
    private Set<Medicine> medicines;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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