package com.example.demo;

import com.example.demo.security.SecurityContext;
import com.example.demo.service.AbacService;
import com.example.demo.service.PermissionService;
import com.example.demo.service.UserContributionService;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {
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
	void getUserData() {
		this.userService.getUserByUsername("liuk1");
//		this.userService.getUserById(1L);
	}
	@Test
	void getAbacData() {
		this.abacService.getAll();
	}

}
