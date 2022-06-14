package ru.regiuss.server.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.regiuss.server.model.Permission;
import ru.regiuss.server.model.Role;
import ru.regiuss.server.model.User;
import ru.regiuss.server.repository.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class DatabaseUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public DatabaseUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userRepository.getByLogin(username);
            if(user == null)return null;
            Set<Permission> permissions = new HashSet<>();
            for(Role r : user.getRoles()){
                permissions.addAll(r.getPermissions());
            }
            return new org.springframework.security.core.userdetails.User(
                    user.getLogin(),
                    user.getPass(),
                    permissions.stream().map(p -> new SimpleGrantedAuthority(p.getName())).toList()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
