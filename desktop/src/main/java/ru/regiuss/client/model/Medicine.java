package ru.regiuss.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Medicine {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;

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
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicine medicine = (Medicine) o;
        return Objects.equals(id, medicine.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}