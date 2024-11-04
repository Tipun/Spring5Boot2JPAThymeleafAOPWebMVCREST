package com.jrp.pma.security;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)

public class SecurityConfig  {
	
	@Autowired
	DataSource dataSource;
	
	
	
	@Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        String userByUserNameQuery = "select username, password, enabled from user_accounts where username = ?";
        String authoritiesByUserQuery = "select username, role from user_accounts where username = ?";
 var userDetailsManager = new JdbcUserDetailsManager(dataSource);
 userDetailsManager.setUsersByUsernameQuery(userByUserNameQuery);
 userDetailsManager.setAuthoritiesByUsernameQuery(authoritiesByUserQuery);
        return userDetailsManager;
    }

	
//	@Bean
//	public UserDetailsService userDetailsService(BCryptPasswordEncoder bCryptPasswordEncoder) {
//	    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//	    manager.createUser(User.withUsername("user")
//	      .password(bCryptPasswordEncoder.encode("pass"))
//	      .roles("USER") 
//	      .build());
//	    manager.createUser(User.withUsername("Tipun")
//	  	      .password(bCryptPasswordEncoder.encode("pass"))
//	  	      .roles("USER") 
//	  	      .build());
//	    manager.createUser(User.withUsername("admin")
//	      .password(bCryptPasswordEncoder.encode("pass"))
//	      .roles("USER", "ADMIN")
//	      .build());
//	    return manager;
//	}

	@Bean
	 public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
	  
	   return http.csrf(AbstractHttpConfigurer::disable)
	    // Here we are configuring our login form
	 .formLogin(Customizer.withDefaults())
	 .authorizeHttpRequests(authorize ->
	 authorize
	        // We are permitting all static resources to be accessed publicly
	 .requestMatchers("/images/**", "/css/**", "/js/**", "/WEB-INF/views/**").permitAll()
	        // We are restricting endpoints for individual roles.
	 //.requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
//     .requestMatchers("/projects/new").hasRole("ADMIN")
//     .requestMatchers("/projects/save").hasRole("ADMIN")
//     .requestMatchers("/employees/new").hasRole("ADMIN")
//     .requestMatchers("/employees/save").hasRole("ADMIN")
     .requestMatchers("/", "/**").permitAll()
	        // Following line denotes that all requests must be authenticated.
	        // Hence, once a request comes to our application, we will check if the user is authenticated or not.
	 .anyRequest().authenticated()
	 )

	 .logout(Customizer.withDefaults())
	 .build();

	 }
	
//	
//
//
//	private boolean securityDebug;
//
//	@Bean
//	public WebSecurityCustomizer webSecurityCustomizer() {
//	  	return web -> web.debug(securityDebug).ignoring().requestMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/favicon.ico");
//	}
}