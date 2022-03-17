package ru.regiuss.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.regiuss.server.model.User;

public interface UserRepository extends JpaRepository<User, Integer>, UserRepositoryCustom {

}
