package ru.regiuss.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.regiuss.server.model.Reception;
import ru.regiuss.server.repository.custom.ReceptionRepositoryCustom;

import java.util.List;

public interface ReceptionRepository extends JpaRepository<Reception, Integer>, ReceptionRepositoryCustom {
    List<Reception> findAllByStatus(Integer status);
}
