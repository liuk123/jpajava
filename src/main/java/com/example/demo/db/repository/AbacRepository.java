package com.example.demo.db.repository;

import com.example.demo.db.model.Abac;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AbacRepository extends JpaRepository<Abac, Long> {
    Abac findFirstById(Long id);
    List<Abac> findByPermissions_Id(Long id);
}
