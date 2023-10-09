package com.example.demo;

import com.example.demo.db.model.Abac;
import com.example.demo.db.model.User;
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
    void testRbac(){
        User user = userService.getUserById(1L);
        List<Abac> rbac = abacService.getAll();
        List<String> permissions = securityContext.rbacPermissions(user, rbac);
        System.out.println(permissions);

        user = userService.getUserById(2L);
        permissions = securityContext.rbacPermissions(user, rbac);
        System.out.println(permissions);

        List<String> permissions1 = securityContext.rbacPermissions(user, rbac, getMetadataCustomizer());
        System.out.println(permissions1);
    }

    private List<MetadataCustomizer> getMetadataCustomizer() {
        return new ArrayList<>() {{
            add(user -> user.getMetadata().put("ip", "192.168.0.1"));
        }};
    }

    @Test
    void testUserData(){
        User user = this.userService.getUserByUsername("liuk1");
        System.out.println(user);

    }
    @Test
    void testJpaRemove(){
//        this.abacService.delOne(102L);
//        this.userService.delOne(1L);
//        this.permissionService.delOne(1L);
//        this.abacService.delOne(52L);
//        this.abacService.save(new Abac(52L, "userName=='liukai'",  List.of(p1,p2,p3)));
        this.abacService.delByPermissionId(1L);
    }

}
