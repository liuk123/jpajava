package com.example.demo.db.repository;

import com.example.demo.db.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("UserRepository")
public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstById(Long id);
//    @EntityGraph(value = "UserEntity", type = EntityGraph.EntityGraphType.FETCH)
    User findByUsername(String username);
}
