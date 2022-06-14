package ru.regiuss.server.controller.api;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.regiuss.server.Views;
import ru.regiuss.server.model.User;
import ru.regiuss.server.service.UserService;
import ru.regiuss.server.vactination.core.Permissions;

import javax.servlet.http.HttpServletRequest;

@RestController()
@RequestMapping("/api")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PreAuthorize("hasAuthority('" + Permissions.USERS_GET_ALL + "')")
    @GetMapping("/v1/users")
    public Page<User> all(
            Pageable pageable,
            @RequestParam(required = false) int[] laboratories
    ) {
        return service.getAll(pageable, laboratories);
    }

    @PreAuthorize("hasAuthority('" + Permissions.USERS_GET + "')")
    @GetMapping("/v1/users/{userId}")
    public User get(@PathVariable Integer userId){
        return service.get(userId);
    }

    @GetMapping("/v1/users/current")
    @JsonView(Views.Full.class)
    public User current(HttpServletRequest request){
        String username = request.getUserPrincipal().getName();
        return service.get(username);
    }

    @PreAuthorize("hasAuthority('" + Permissions.USERS_EDIT + "')")
    @PutMapping("/v1/users/{userId}")
    public User update(@PathVariable Integer userId, @RequestBody User newUser){
        return service.update(userId, newUser);
    }

    @PreAuthorize("hasAuthority('" + Permissions.USERS_DELETE + "')")
    @DeleteMapping("/v1/users/{userId}")
    public void delete(@PathVariable Integer userId){
        service.delete(userId);
    }

    @PreAuthorize("hasAuthority('" + Permissions.USERS_CREATE + "')")
    @PostMapping("/v1/users")
    public User create(@RequestBody User newUser){
        return newUser;
    }
}
