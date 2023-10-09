package com.example.demo.service;

import com.example.demo.db.model.Menu;
import com.example.demo.db.model.Permission;
import com.example.demo.db.repository.MenuRepository;
import com.example.demo.db.repository.PermissionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MenuService {
    final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }
    public void delOne(Long id){
        this.menuRepository.deleteById(id);
    }
    public List<Menu> getMenuWithPermission(){
        return this.menuRepository.findAll();
    }
}
