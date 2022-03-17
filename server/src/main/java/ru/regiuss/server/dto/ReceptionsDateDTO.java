package ru.regiuss.server.dto;

import com.fasterxml.jackson.annotation.JsonView;
import ru.regiuss.server.Views;
import ru.regiuss.server.model.Reception;

import java.time.Instant;
import java.util.List;

public class ReceptionsDateDTO {
    @JsonView(Views.Simple.class)
    private Instant date;
    @JsonView(Views.Simple.class)
    private List<Reception> receptions;

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public List<Reception> getReceptions() {
        return receptions;
    }

    public void setReceptions(List<Reception> receptions) {
        this.receptions = receptions;
    }
}
