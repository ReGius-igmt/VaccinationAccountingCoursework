package ru.regiuss.server.service;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.stereotype.Service;
import ru.regiuss.server.Views;
import ru.regiuss.server.dto.ReceptionsDateDTO;
import ru.regiuss.server.exception.CustomException;
import ru.regiuss.server.model.Reception;
import ru.regiuss.server.repository.ReceptionRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.Instant;
import java.util.List;
import java.util.Map;

@Service
public class ReceptionService {
    private final ReceptionRepository repository;

    public ReceptionService(ReceptionRepository repository) {
        this.repository = repository;
    }

    public List<Reception> getAll(Integer status, Integer limit, Long afterD, Long beforeD){
        return repository.findAll(status);
    }

    public Reception get(Integer id) {
        return repository.findById(id).orElseThrow(() -> new CustomException(1, "Reception no found", "Reception no found"));
    }

    public void save(Reception reception) {
        repository.saveAndFlush(reception);
    }
}
