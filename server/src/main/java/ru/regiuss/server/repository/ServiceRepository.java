package ru.regiuss.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.regiuss.server.model.Service;

public interface ServiceRepository extends JpaRepository<Service, Integer> {
}
