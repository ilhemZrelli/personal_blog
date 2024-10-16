package com.blog.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.blog.demo.model.Role;
import com.blog.demo.model.User;
import com.blog.demo.services.UserService;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class DemoApplication {
	
	//@Autowired
	//UserService userService;

	/*@PostConstruct
	void init_users() {
		// ajouter les rôles
		userService.addRole(new Role("ADMIN"));
		userService.addRole(new Role("USER"));
		// ajouter les users
		userService.registerUser(new User("admin", "123", "ilhem@gmail.com"));
		userService.registerUser(new User("user", "12345", "ilhem@gmail.com"));
		userService.registerUser(new User("admin2", "123", "ilhem@gmail.com"));

		// ajouter les rôles aux users
		userService.addRoleToUser("admin", "ADMIN");
		userService.addRoleToUser("user", "USER");
		userService.addRoleToUser("admin2", "ADMIN");
	}*/
  
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
