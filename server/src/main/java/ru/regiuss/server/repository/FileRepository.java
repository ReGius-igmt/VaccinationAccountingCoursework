package ru.regiuss.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.regiuss.server.model.File;

public interface FileRepository extends JpaRepository<File, Integer> {
}