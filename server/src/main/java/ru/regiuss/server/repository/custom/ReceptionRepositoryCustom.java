package ru.regiuss.server.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.regiuss.server.model.Reception;

public interface ReceptionRepositoryCustom {
    Page<Reception> findAll(Pageable pageable, int[] laboratories, Long afterDate, Integer status, Long beforeDate);
}
