package com.example.demo.service;

import com.example.demo.model.Permission;
import com.example.demo.repository.PermissionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
