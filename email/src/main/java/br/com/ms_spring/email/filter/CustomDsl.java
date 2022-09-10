package br.com.ms_spring.email.filter;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class CustomDsl extends AbstractHttpConfigurer<CustomDsl, HttpSecurity> {

    //private boolean flag;

	@Override
	public void init(HttpSecurity http) throws Exception {
		// any method that adds another configurer
		// must be done in the init method
		http.httpBasic()
            .and()
            .authorizeRequests()
        	.antMatchers(HttpMethod.POST, "/api/login/**").permitAll()
			// .antMatchers(HttpMethod.GET, "/api/users").permitAll()
            // .antMatchers(HttpMethod.POST, "/api/user/save").permitAll()
            // .antMatchers(HttpMethod.POST, "/api/role/save").permitAll()
            // .antMatchers(HttpMethod.POST, "/api/role/addToUser").permitAll()
            .anyRequest().authenticated()
            .and()
            .csrf().disable();
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		//ApplicationContext context = http.getSharedObject(ApplicationContext.class);
		AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
		// here we lookup from the ApplicationContext. You can also just create a new instance.
		//CustomAuthenticationFilter customFilter = context.getBean(CustomAuthenticationFilter.class);

		http.addFilter(new CustomAuthenticationFilter(authenticationManager));
		http.addFilterBefore(new CustomAuthorizationFilter(),UsernamePasswordAuthenticationFilter.class);

	}

	/* public CustomDsl flag(boolean value) {
		this.flag = value;
		return this;
	} */

	public static CustomDsl customDsl() {
		return new CustomDsl();
	}
    
}