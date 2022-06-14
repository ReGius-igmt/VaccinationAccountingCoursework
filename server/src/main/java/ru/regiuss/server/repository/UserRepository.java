package ru.regiuss.server.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.regiuss.server.model.User;
import ru.regiuss.server.repository.custom.UserRepositoryCustom;

public interface UserRepository extends JpaRepository<User, Integer>, UserRepositoryCustom {
    User getByLogin(String login);
}
