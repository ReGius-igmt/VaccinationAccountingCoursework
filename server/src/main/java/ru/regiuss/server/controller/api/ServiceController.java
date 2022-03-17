package ru.regiuss.server.controller.api;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.*;
import ru.regiuss.server.Views;
import ru.regiuss.server.model.Reception;
import ru.regiuss.server.model.Service;
import ru.regiuss.server.service.ServiceService;

@RestController
@RequestMapping("/api")
public class ServiceController {
    private final ServiceService service;

    public ServiceController(ServiceService service) {
        this.service = service;
    }

    @GetMapping("/v1/services/{id}")
    @JsonView(Views.Full.class)
    public Service get(@PathVariable Integer id) {
        return service.get(id);
    }
}
