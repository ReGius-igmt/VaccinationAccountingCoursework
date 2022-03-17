package ru.regiuss.server.repository;

import ru.regiuss.server.dto.ReceptionsDateDTO;
import ru.regiuss.server.model.Reception;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public interface ReceptionRepositoryCustom {
    List<Reception> findAll(Integer status);
}
