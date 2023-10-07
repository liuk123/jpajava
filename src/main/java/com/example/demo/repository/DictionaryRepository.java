package com.example.demo.repository;

import com.example.demo.model.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DictionaryRepository extends JpaRepository<Dictionary, Long> {
    Dictionary findFirstById(Long id);
}
