package br.com.ms_spring.email.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Service;

import br.com.ms_spring.email.controllers.RegistrationRequest;
import br.com.ms_spring.email.models.RoleModel;
import br.com.ms_spring.email.models.UserModel;
import br.com.ms_spring.email.repositories.RoleRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final UserService userService;
    private final EmailValidator emailValidator;
    private final RoleRepository roleRepository;
    
    public String register (RegistrationRequest request) {
        Boolean isValidEmail = emailValidator.test(request.getEmail());

        if (!isValidEmail) {
            throw new IllegalStateException("Email not valide!");
        }
        RoleModel role = roleRepository.findByName("ROLE_USER");
        Collection<RoleModel> roles = new ArrayList<>();
        roles.add(role);
        return userService.signUpUser(new UserModel(null,
            request.getName(),
            request.getUserName(),
            request.getPassword(),
            request.getEmail(),
            LocalDateTime.now(),
            roles,
            false,
            false
        ));
    }
    
}
