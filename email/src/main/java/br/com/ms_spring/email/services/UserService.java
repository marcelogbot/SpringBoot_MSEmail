package br.com.ms_spring.email.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.ms_spring.email.models.RoleModel;
import br.com.ms_spring.email.models.UserModel;
import br.com.ms_spring.email.repositories.RoleRepository;
import br.com.ms_spring.email.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service 
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public UserModel saveUser(UserModel userModel) {
        
        UserModel userFinded = userRepository.findByUserName(userModel.getUserName());
        if (userFinded == null) {
            log.info("New user save in database - "+userModel.getUserName());
            return userRepository.save(userModel);
        } else {
            log.info("User already exists - "+userModel.getUserName());
            return userModel;
        }
    }

    public RoleModel saveRole(RoleModel roleModel) {
        RoleModel roleFinded = roleRepository.findByName(roleModel.getName());
        if (roleFinded == null) {
            log.info("New role save in database - "+roleModel.getName());
            return roleRepository.save(roleModel);
        } else {
            log.info("Role already exists - "+roleModel.getName());
            return roleModel;
        }
        
    }

    public void addRoleToUser(String userName, String roleName) {
        
        UserModel userModel = userRepository.findByUserName(userName);
        RoleModel roleModel = roleRepository.findByName(roleName);
        List<RoleModel> rolesUser = new ArrayList<>(userModel.getRoles());

        if (rolesUser.contains(roleModel)) {
            log.info("Role already exists - "+roleModel.getName());
        } else {
            log.info("Adding role ("+roleName+") to user ("+userName+")");
            userModel.getRoles().add(roleModel);
            userRepository.save(userModel);
        }
    }

    public UserModel getUser(String userName) {
        log.info("Get user: "+ userName);
        return userRepository.findByUserName(userName);
    }

    public List<UserModel> getAllUsers(){
        log.info("List all users em");
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = userRepository.findByUserName(username);

        if (userModel == null) {
            log.error("User not found");
            throw new UsernameNotFoundException("User not found");
        } else {
            log.info("User found" + userModel.getUserName());
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        userModel.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(userModel.getUserName(), userModel.getPassword(), authorities);
    }
}