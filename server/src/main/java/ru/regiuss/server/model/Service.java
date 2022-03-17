package ru.regiuss.server.model;

import com.fasterxml.jackson.annotation.JsonView;
import ru.regiuss.server.Views;

import javax.persistence.*;
import java.util.List;

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

    @Column(name = "qr_e", nullable = false)
    @JsonView(Views.Simple.class)
    private Integer qrE;

    @JsonView(Views.Full.class)
    @JoinTable(name = "medicines_services", joinColumns = @JoinColumn(name = "service_id"),
    inverseJoinColumns = @JoinColumn(name = "medicine_id"))
    @ManyToMany
    private List<Medicine> medicines;

    public Integer getQrE() {
        return qrE;
    }

    public void setQrE(Integer qrE) {
        this.qrE = qrE;
    }

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

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }
}