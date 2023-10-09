package com.example.demo.db.repository;

import com.example.demo.db.model.Abac;
import com.example.demo.db.model.Menu;
import lombok.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Menu findFirstById(Long id);
    @EntityGraph(value = "MenuEntity", type = EntityGraph.EntityGraphType.FETCH)
    List<Menu> findByPermissions_Id(Long id);

    @EntityGraph(value = "MenuEntity", type = EntityGraph.EntityGraphType.FETCH)
    @NonNull
    List<Menu> findAll();
}
