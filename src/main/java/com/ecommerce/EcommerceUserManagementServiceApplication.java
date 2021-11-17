package com.ecommerce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ecommerce.role.Role;
import com.ecommerce.role.RoleRepository;

@SpringBootApplication
public class EcommerceUserManagementServiceApplication {
	
	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(EcommerceUserManagementServiceApplication.class, args);
	}
	
	@Bean
	protected CommandLineRunner runCommandLineRunner() {
		return (String... arr)->{
			 this.addRole();
		};
	}
	
	private void addRole() {
		 if(this.roleRepository.count()==0) {
			  List.of("Admin","Buyer").forEach(roleName -> {
				   Role role = new Role();
				   role.setActive(true);
				   role.setRoleName(roleName);
				   this.roleRepository.save(role);
			  });
		 }
	}

}
