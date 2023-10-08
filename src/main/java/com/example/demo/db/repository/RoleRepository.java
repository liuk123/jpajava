package com.example.demo.db.repository;

import com.example.demo.db.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findFirstById(Long id);
}
