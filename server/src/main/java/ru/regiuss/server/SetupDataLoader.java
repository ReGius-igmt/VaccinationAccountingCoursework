package ru.regiuss.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.regiuss.server.repository.UserRepository;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

    }
}
