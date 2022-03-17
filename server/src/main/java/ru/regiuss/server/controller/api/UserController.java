package ru.regiuss.server.controller.api;

import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;
import ru.regiuss.server.model.User;
import ru.regiuss.server.service.UserService;

@RestController()
@RequestMapping("/api")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/v1/users")
    public PageImpl<User> all(
            @RequestParam(defaultValue = "100") Integer limit,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(required = false) String role
    ) {
        return service.getAll(limit, page, sort, role);
    }

    @GetMapping("/v1/users/{userId}")
    public User get(@PathVariable Integer userId){
        return service.get(userId);
    }

    @PutMapping("/v1/users/{userId}")
    public User update(@PathVariable Integer userId, @RequestBody User newUser){
        return service.update(userId, newUser);
    }

    @DeleteMapping("/v1/users/{userId}")
    public void delete(@PathVariable Integer userId){
        service.delete(userId);
    }

    @PostMapping("/v1/users")
    public User create(@RequestBody User newUser){
        return newUser;
    }
}
