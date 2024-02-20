package com.app.demo;

import com.app.demo.entities.Role;
import com.app.demo.repositories.RoleRepository;
import com.app.demo.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.CodeSigner;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			if (roleRepository.existsByAuthority(Constants.USER) && roleRepository.existsByAuthority(Constants.ADMIN)) {
				return;
			}

			roleRepository.save(new Role(Constants.USER));
			roleRepository.save(new Role(Constants.ADMIN));

		};
	}

}
