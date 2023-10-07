package com.example.demo.service;

import com.example.demo.db.model.Permission;
import com.example.demo.db.repository.PermissionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PermissionService {
    final PermissionRepository permissionRepository;

    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }
    public void delOne(Long id){
        this.permissionRepository.deleteById(id);
    }
    public Permission getOne(long id){
        return this.permissionRepository.findFirstById(id);
    }
}
