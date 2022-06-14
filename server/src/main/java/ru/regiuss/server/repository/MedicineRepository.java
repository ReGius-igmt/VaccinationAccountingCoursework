package ru.regiuss.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.regiuss.server.model.Medicine;

public interface MedicineRepository extends JpaRepository<Medicine, Integer> {
}