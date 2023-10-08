package com.example.demo.service;

import com.example.demo.db.model.Abac;
import com.example.demo.db.repository.AbacRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class AbacService {
    private final AbacRepository abacRepository;

    public AbacService(AbacRepository abacRepository) {
        this.abacRepository = abacRepository;
    }
    @Transactional(readOnly = true)
    public List<Abac> getAll(){
        return this.abacRepository.findAll();
    }
    public void delOne(Long id){
        this.abacRepository.deleteById(id);
    }
    public Abac save(Abac abac){
        return this.abacRepository.save(abac);
    }

    @Transactional
    public void delByPermissionId(Long id){
        List<Abac> abacs = this.abacRepository.findByPermissions_Id(id);
        for(Abac abac:abacs){
            abac.getPermissions().removeIf(permission -> Objects.equals(permission.getId(), id));
            abacRepository.save(abac);
        }
    }
}
