package br.com.ms_spring.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmailApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailApplication.class, args);
	}

/* 	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.saveRole(new RoleModel(null,"ROLE_USER"));
			userService.saveRole(new RoleModel(null,"ROLE_ADMIN"));
			
			userService.saveUser(new UserModel(null, "Marcelo Botelho",
											 "marcelo", "1234", "marcunb@gmail.com",
											  LocalDateTime.now(), new ArrayList<>(), true, true));
			
			userService.addRoleToUser("marcelo", "ROLE_ADMIN");
			userService.addRoleToUser("marcelo", "ROLE_USER");
		};
	} */


}
