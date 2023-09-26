package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstById(Long id);
    @EntityGraph(value = "UserEntity", type = EntityGraph.EntityGraphType.FETCH)
    User findByUsername(String username);
}
