package com.example.demo.db.repository;

import com.example.demo.db.model.Abac;
import lombok.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNullApi;

import java.util.List;

public interface AbacRepository extends JpaRepository<Abac, Long> {
    Abac findFirstById(Long id);
    @EntityGraph(value = "AbacEntity", type = EntityGraph.EntityGraphType.FETCH)
    List<Abac> findByPermissions_Id(Long id);

    @EntityGraph(value = "AbacEntity", type = EntityGraph.EntityGraphType.FETCH)
    @NonNull
    List<Abac> findAll();
}
