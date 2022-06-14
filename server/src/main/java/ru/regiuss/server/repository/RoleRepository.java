package ru.regiuss.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.regiuss.server.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}