package com.example.demo.db.repository;

import com.example.demo.db.model.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DictionaryRepository extends JpaRepository<Dictionary, Long> {
    Dictionary findFirstById(Long id);
}
