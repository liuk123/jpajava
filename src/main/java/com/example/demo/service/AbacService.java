package com.example.demo.service;

import com.example.demo.model.Abac;
import com.example.demo.repository.AbacRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AbacService {
    private final AbacRepository abacRepository;

    public AbacService(AbacRepository abacRepository) {
        this.abacRepository = abacRepository;
    }
    public List<Abac> getAll(){
        return this.abacRepository.findAll();
    }
    public void delOne(Long id){
        this.abacRepository.deleteById(id);
    }
    public Abac save(Abac abac){
        return this.abacRepository.saveAndFlush(abac);
    }
}
