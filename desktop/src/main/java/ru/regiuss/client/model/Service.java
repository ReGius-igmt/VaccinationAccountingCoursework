package ru.regiuss.client.model;

import java.util.List;

public class Service {
    private Integer id;
    private String name;
    private List<Medicine> medicines;
    private Integer qrE;

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

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", medicines=" + medicines +
                ", qrE=" + qrE +
                '}';
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }

}