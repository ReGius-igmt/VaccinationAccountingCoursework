package ru.regiuss.server.service;

import ru.regiuss.server.model.Service;
import ru.regiuss.server.repository.ServoceRepository;

@org.springframework.stereotype.Service
public class ServiceService {
    private final ServoceRepository repository;

    public ServiceService(ServoceRepository repository) {
        this.repository = repository;
    }

    public Service get(Integer id){
        return repository.findById(id).get();
    }
}
