package ru.regiuss.server.service;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.regiuss.server.exception.PageSizeException;
import ru.regiuss.server.exception.UserNoFoundException;
import ru.regiuss.server.model.User;
import ru.regiuss.server.repository.UserRepository;

import java.util.Objects;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public PageImpl<User> getAll(Integer limit, Integer page, String sort, String role){
        if(limit <= 0)throw new PageSizeException();
        if(limit > 100)limit = 100;
        Pageable pageable = PageRequest.of(page, limit);
        return repository.findAll(pageable, sort, role);
    }

    public User get(Integer id){
        return repository.findById(id)
                .orElseThrow(() -> new UserNoFoundException(1, "Server error", String.format("User [%s] no found", id)));
    }

    public User save(User user){
        return repository.saveAndFlush(user);
    }

    public User update(Integer id, User user){
        if(!Objects.equals(id, user.getId()))throw new RuntimeException("Id not equals");
        return repository.saveAndFlush(user);
    }

    public void delete(Integer id){
        repository.deleteById(id);
    }
}
