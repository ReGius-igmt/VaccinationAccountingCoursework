package ru.regiuss.server.service;

import ru.regiuss.server.model.Service;
import ru.regiuss.server.repository.ServiceRepository;

@org.springframework.stereotype.Service
public class ServiceService {
    private final ServiceRepository repository;

    public ServiceService(ServiceRepository repository) {
        this.repository = repository;
    }

    public Service get(Integer id){
        return repository.findById(id).get();
    }
}
