package com.foodhunt.auth_service.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        http
                .csrf(c->c.disable())
                .authorizeHttpRequests(auth->
                        auth.requestMatchers("/public/**").permitAll()
                                .anyRequest().authenticated())
                                .oauth2ResourceServer(outh2->
                                        outh2.jwt(Customizer.withDefaults()));
        return http.build();
    }
}
