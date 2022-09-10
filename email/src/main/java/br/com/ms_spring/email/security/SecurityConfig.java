package br.com.ms_spring.email.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import br.com.ms_spring.email.filter.CustomDsl;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

         http//.httpBasic()
        //     .and()
        //     .authorizeRequests()
        //     // .antMatchers(HttpMethod.GET, "/api/users").permitAll()
        //     // .antMatchers(HttpMethod.POST, "/api/user/save").permitAll()
        //     // .antMatchers(HttpMethod.POST, "/api/role/save").permitAll()
        //     // .antMatchers(HttpMethod.POST, "/api/role/addToUser").permitAll()
        //     .anyRequest().authenticated()
        //     .and()
        //     .csrf().disable()
             .apply(customDsl());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static CustomDsl customDsl() {
        return new CustomDsl();
    }

}