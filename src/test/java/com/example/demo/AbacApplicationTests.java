package com.example.demo;

import com.example.demo.model.Abac;
import com.example.demo.model.Permission;
import com.example.demo.model.User;
import com.example.demo.security.MetadataCustomizer;
import com.example.demo.security.SecurityContext;
import com.example.demo.service.AbacService;
import com.example.demo.service.PermissionService;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class AbacApplicationTests {
    @Autowired
    private UserService userService;
    @Autowired
    private AbacService abacService;
    @Autowired
    private SecurityContext securityContext;
    @Autowired
    private PermissionService permissionService;

    @Test
    void initData(){

    }

    @Test
    void testRbac(){
        User user = userService.get(1L);
        List<Abac> rbac = abacService.getAll();
        List<String> permissions = securityContext.rbacPermissions(user, rbac);
        System.out.println(permissions);

        user = userService.get(2L);
        permissions = securityContext.rbacPermissions(user, rbac);
        System.out.println(permissions);

        user = userService.get(3L);
        permissions = securityContext.rbacPermissions(user, rbac);
        System.out.println(permissions);
    }

    @Test
    void testMetadataCustomizer(){
        User user = userService.get(1L);
        List<Abac> rbac = abacService.getAll();

        List<String> permissions = securityContext.rbacPermissions(user, rbac);
        System.out.println(permissions);

        permissions = securityContext.rbacPermissions(user, rbac, getMetadataCustomizer());
        System.out.println(permissions);
    }

    private List<MetadataCustomizer> getMetadataCustomizer() {
        return new ArrayList<MetadataCustomizer>() {{
            add(user -> user.getMetadata().put("ip", "192.168.0.1"));
        }};
    }

    @Test
    void testJpaSave(){
        Permission permission = new Permission();
        permission.setId(1L);
        permission.setPermission("npm:update21");
        List<Permission> permissions = List.of(permission);
        Abac abac = new Abac();
        abac.setId(1L);
        abac.setExpression("loginName=='liuk41'");
        abac.setPermissions(permissions);
        Abac ret = this.abacService.save(abac);
        System.out.println(ret);
    }
    @Test
    void testJpaRemove(){
//        this.abacService.delOne(102L);
//        this.userService.delOne(1L);
        this.permissionService.delOne(202L);
    }

}
