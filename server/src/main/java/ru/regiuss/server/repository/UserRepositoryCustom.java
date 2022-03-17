package ru.regiuss.server.repository;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import ru.regiuss.server.model.User;

public interface UserRepositoryCustom {
    PageImpl<User> findAll(Pageable pageable, String sort, String role);
}
