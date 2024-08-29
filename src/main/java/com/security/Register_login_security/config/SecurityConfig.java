package com.security.Register_login_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.http.HttpSession;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService getDetailsService() {
		return new CustomUserDetailsService();
	}

	@Bean
	public DaoAuthenticationProvider getAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(getDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()) // Disable CSRF protection (if needed)
				.authorizeHttpRequests(
						authorize -> authorize.requestMatchers("/", "/login", "/register", "/saveUser").permitAll() 			// specific
																												
								.requestMatchers("/user/**").authenticated() // Require authentication for any other
																				// request
				).formLogin(formLogin -> formLogin.loginPage("/login") // Specify the login page URL
						.loginProcessingUrl("/userLogin") // URL to submit username and password
						// .usernameParameter("email")
						.defaultSuccessUrl("/user/profile", true) // Redirect after successful login
						.permitAll() // Allow all to access the login page
				).logout(logout -> logout.logoutUrl("/logout") // URL to trigger logout
						.logoutSuccessUrl("/") // Redirect to home page after logout
						.permitAll() // Allow all to access the logout URL
				);

		return http.build(); // Return the built SecurityFilterChain object
	}

}
