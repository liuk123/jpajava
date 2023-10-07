package com.example.demo;

import com.example.demo.db.model.Abac;
import com.example.demo.db.model.Permission;
import com.example.demo.db.model.User;
import com.example.demo.db.model.UserContribution;
import com.example.demo.security.MetadataCustomizer;
import com.example.demo.security.SecurityContext;
import com.example.demo.service.AbacService;
import com.example.demo.service.PermissionService;
import com.example.demo.service.UserContributionService;
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
    @Autowired
    private UserContributionService userContributionService;


    @Test
    void testRbac(){
        User user = userService.getUserById(1L);
        List<Abac> rbac = abacService.getAll();
        List<String> permissions = securityContext.rbacPermissions(user, rbac);
        System.out.println(permissions);

        user = userService.getUserById(2L);
        permissions = securityContext.rbacPermissions(user, rbac);
        System.out.println(permissions);
    }

    @Test
    void testMetadataCustomizer(){
        User user = userService.getUserById(1L);
        List<Abac> rbac = abacService.getAll();

        List<String> permissions = securityContext.rbacPermissions(user, rbac);
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
    void testJpaSave(){
        Permission permission = new Permission();
        permission.setId(1L);
        permission.setPermission("npm:update1");
        List<Permission> permissions = List.of(permission);
        Abac abac = new Abac();
        abac.setId(1L);
        abac.setExpression("metadata.get('ip')=='192.168.0.1'");
        abac.setPermissions(permissions);
        Abac retAbac = this.abacService.save(abac);

        Permission permission1 = new Permission();
        permission1.setId(2L);
        permission1.setPermission("npm:update2");
        List<Permission> permissions1 = List.of(permission1);
        Abac abac1 = new Abac();
        abac1.setId(2L);
        abac1.setExpression("contributions.contains('baidu/com')");
        abac1.setPermissions(permissions1);
        Abac retAbac1 = this.abacService.save(abac1);

        User user = new User();
        user.setId(1L);
        user.setName("Liukai");
        user.setUsername("Liuk1");
        UserContribution userContribution = new UserContribution();
        userContribution.setId(1L);
        userContribution.setRepository("333.com");
        userContribution.setUser(user);
        UserContribution retUserContribution = this.userContributionService.save(userContribution);


        User user1 = new User();
        user1.setId(2L);
        user1.setName("test");
        user1.setUsername("test1");
        UserContribution userContribution1 = new UserContribution();
        userContribution1.setId(2L);
        userContribution1.setRepository("baidu/com");
        userContribution1.setUser(user1);
        UserContribution retUserContribution1 = this.userContributionService.save(userContribution1);
    }
    @Test
    void testJpaRemove(){
//        this.abacService.delOne(102L);
//        this.userService.delOne(1L);
//        this.permissionService.delOne(152L);
//        this.abacService.delOne(52L);
//        Permission p1 = this.permissionService.getOne(152L);
//        Permission p2 = this.permissionService.getOne(52L);
//        Permission p3 = this.permissionService.getOne(203L);
//        this.abacService.save(new Abac(52L, "userName=='liukai'",  List.of(p1,p2,p3)));
        this.abacService.delByPermissionId(1L);
    }

}
