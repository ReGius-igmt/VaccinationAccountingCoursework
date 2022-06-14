package ru.regiuss.server.controller.api;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.regiuss.server.Views;
import ru.regiuss.server.model.Reception;
import ru.regiuss.server.service.ReceptionService;

import java.time.Instant;
import java.util.List;

@RestController()
@RequestMapping("/api")
public class ReceptionsController {

    private final ReceptionService service;

    public ReceptionsController(ReceptionService service) {
        this.service = service;
    }

    @GetMapping("/v1/receptions")
    public Page<Reception> all(
            Pageable pageable,
            @RequestParam(required = false) int[] laboratories,
            @RequestParam(required = false) Long afterDate,
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false) Long beforeDate
    ) {
        return service.getAll(pageable, laboratories, afterDate, status, beforeDate);
    }

    @GetMapping("v1/receptions/{id}")
    @JsonView(Views.Simple.class)
    public Reception get(@PathVariable Integer id) {
        return service.get(id);
    }


    @PutMapping("v1/receptions/{id}")
    @JsonView(Views.Simple.class)
    public Reception update(@PathVariable("id") Reception receptionFromBd, @RequestBody Reception reception) {
        System.out.println("DATA GET :" + reception);
        //receptionFromBd.setDate(Instant.now());
        receptionFromBd.setStatus(reception.getStatus());
        receptionFromBd.setMedicines(reception.getMedicines());
        receptionFromBd.setComment(reception.getComment());
        service.save(receptionFromBd);
        return receptionFromBd;
    }
}
