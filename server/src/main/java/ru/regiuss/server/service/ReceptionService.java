package ru.regiuss.server.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.regiuss.server.exception.CustomException;
import ru.regiuss.server.model.Reception;
import ru.regiuss.server.repository.ReceptionRepository;

import java.util.List;

@Service
public class ReceptionService {
    private final ReceptionRepository repository;

    public ReceptionService(ReceptionRepository repository) {
        this.repository = repository;
    }

    public Page<Reception> getAll(Pageable pageable, int[] laboratories, Long afterDate, Integer status, Long beforeDate){
        if(pageable.getPageSize() > 100)pageable = PageRequest.of(pageable.getPageNumber(), 100, pageable.getSort());
        return repository.findAll(pageable, laboratories, afterDate, status, beforeDate);
    }

    public Reception get(Integer id) {
        return repository.findById(id).orElseThrow(() -> new CustomException(1, "Reception no found", "Reception no found"));
    }

    public void save(Reception reception) {
        repository.saveAndFlush(reception);
    }
}
