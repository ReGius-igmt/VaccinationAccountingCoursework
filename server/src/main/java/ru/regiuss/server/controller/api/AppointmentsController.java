package ru.regiuss.server.controller.api;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;
import ru.regiuss.server.Views;
import ru.regiuss.server.dto.ReceptionsDateDTO;
import ru.regiuss.server.model.Reception;
import ru.regiuss.server.model.User;
import ru.regiuss.server.service.ReceptionService;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AppointmentsController {

    private final ReceptionService service;

    public AppointmentsController(ReceptionService service) {
        this.service = service;
    }

    @GetMapping("/v1/appointments")
    @JsonView(Views.Simple.class)
    public List<Reception> all(
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false, defaultValue = "100") Integer limit,
            @RequestParam(required = false) Long afterD,
            @RequestParam(required = false) Long beforeD
    ) {
        return service.getAll(status, limit, afterD, beforeD);
    }

    @GetMapping("/v1/appointments/{id}")
    @JsonView(Views.Simple.class)
    public Reception get(@PathVariable Integer id) {
        return service.get(id);
    }


    @PutMapping("/v1/appointments/{id}")
    @JsonView(Views.Simple.class)
    public Reception update(@PathVariable("id") Reception receptionFromBd, @RequestBody Reception reception) {
        System.out.println("DATA GET :" + reception);
        receptionFromBd.setDate(Instant.now());
        receptionFromBd.setStatus(reception.getStatus());
        receptionFromBd.setMedicine(reception.getMedicine());
        service.save(receptionFromBd);
        return receptionFromBd;
    }
}
