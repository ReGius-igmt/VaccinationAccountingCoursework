package ru.regiuss.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.regiuss.server.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
}