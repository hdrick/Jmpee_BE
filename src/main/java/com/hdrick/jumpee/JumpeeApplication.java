package com.hdrick.jumpee;

import com.hdrick.jumpee.model.Role;
import com.hdrick.jumpee.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JumpeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(JumpeeApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataInitializer(RoleRepository roleRepository) {
		return args -> {
			// Check if roles already exist
			if (roleRepository.count() == 0) {
				roleRepository.save(new Role("Admin", "Administrator role with all permissions"));
				roleRepository.save(new Role("User", "Regular user role with standard permissions"));
				roleRepository.save(new Role("Guest", "Guest role with limited access, suitable for temporary users or those with minimal permissions"));
			}
		};
	}

}
