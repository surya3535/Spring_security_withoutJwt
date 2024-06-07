package com.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private MyUserDetailService myUserDetailService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return myUserDetailService;
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		dao.setUserDetailsService(myUserDetailService);
		dao.setPasswordEncoder(passwordEncoder());
		return dao;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(crsf -> crsf.disable())
				.authorizeHttpRequests(auth -> auth.requestMatchers("/home", "/save").permitAll()
						.requestMatchers("/user").hasRole("USER").requestMatchers("/admin").hasRole("ADMIN")
						.anyRequest().authenticated())
//				.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.formLogin(form -> form.permitAll()).logout(log -> log.permitAll()).build();
	}

	// inmemory authentication
//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails user=User.withUsername("surya").password("{noop}123").roles("ADMIN").build();
//		UserDetails admin=User.withUsername("hema").password("{noop}123").roles("USER").build();
//		return new InMemoryUserDetailsManager(user,admin);
//	}

}
