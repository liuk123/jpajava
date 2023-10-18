package com.example.demo.service;
import com.example.demo.db.model.Role;
import com.example.demo.db.model.User;
import com.example.demo.db.repository.UserRepository;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final EntityManager entityManager;

    public UserService(UserRepository userRepository,EntityManager entityManager) {
        this.userRepository = userRepository;
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public User getUserById(Long id){
        User user = this.userRepository.findFirstById(id);
        user.getMetadata().put("roles", user.getRoles().stream().map(Role::getName).toList());
        return user;
    }
    public void save(User user){
        this.userRepository.save(user);
    }
    public void delOne(Long id){
        this.userRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public User getUserWithRoleByUsername(String username){
        User user = this.userRepository.findByUsername(username);
        if(user!=null){
            user.getMetadata().put("roles", user.getRoles().stream().map(Role::getName).toList());
        }
        return user;
    }
}
