package ru.regiuss.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.regiuss.server.model.Laboratory;

public interface LaboratoryRepository extends JpaRepository<Laboratory, Integer> {
}