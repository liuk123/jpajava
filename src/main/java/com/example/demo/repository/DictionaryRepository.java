package com.example.demo.repository;

import com.example.demo.db.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DictionaryRepository extends JpaRepository<Dictionary, Long> {
    Dictionary findFirstById(Long id);
}
