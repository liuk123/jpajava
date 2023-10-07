package com.example.demo.db.repository;

import com.example.demo.db.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Permission findFirstById(Long id);
}
