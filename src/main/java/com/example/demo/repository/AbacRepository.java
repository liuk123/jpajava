package com.example.demo.repository;

import com.example.demo.model.Abac;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbacRepository extends JpaRepository<Abac, Long> {
    Abac findFirstById(Long id);
    void deleteAbac_permissionByPermissions_Id(Long id);
}
