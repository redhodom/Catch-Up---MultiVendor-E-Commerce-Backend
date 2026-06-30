package com.ec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ec.security.JwtFilter;

@Configuration
public class SecurityConfig {

  @Bean
  JwtFilter jwtFilter() {
    return new JwtFilter();
}
  
  @Bean
  BCryptPasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }
  
  @Bean
  public AuthenticationManager authenticationManager(
          AuthenticationConfiguration config)
          throws Exception {

      return config.getAuthenticationManager();
  }
  
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)
        throws Exception {

		 http
	        .csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(auth -> auth

	            .requestMatchers("/auth/**").permitAll()

	            .requestMatchers("/users/**").permitAll()
	            
	            .requestMatchers(
	                    "/swagger-ui/**",
	                    "/v3/api-docs/**",
	                    "/swagger-ui.html"
	            ).permitAll()

	            .requestMatchers("/products/**")
	            .hasAnyRole("ADMIN","SELLER")

	            .requestMatchers("/admin/**")
	            .hasRole("ADMIN")

	            .requestMatchers("/seller/**")
	            .hasRole("SELLER")
	            .anyRequest()
	            .authenticated()
	        )
	        .addFilterBefore(
	                jwtFilter(),
	                UsernamePasswordAuthenticationFilter.class
	        );

	    return http.build();

}
	
	
	
	}
