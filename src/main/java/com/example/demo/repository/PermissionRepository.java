package com.example.demo.repository;

import com.example.demo.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    List<Permission> findByAbacs_Id(Long id);
}
