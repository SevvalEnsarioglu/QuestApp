package com.project1.questapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // CSRF korumasını devre dışı bırakır, bu özellikle PUT ve POST gibi istekler için önemlidir.
            .authorizeHttpRequests((authz) -> authz
                .requestMatchers("/users/**").permitAll()
                    .requestMatchers("/users/**").permitAll()  // `/users` yolundaki tüm isteklerin kimlik doğrulaması olmadan erişilebilmesini sağlar
                    .requestMatchers("/like/**").permitAll()  // `/users` yolundaki tüm isteklerin kimlik doğrulaması olmadan erişilebilmesini sağlar
                    .requestMatchers("/posts/**").permitAll()
                    .anyRequest().authenticated()  // Diğer tüm isteklerin kimlik doğrulaması gerektirdiğini belirtir
            )
            .httpBasic(); // Basic Auth kimlik doğrulaması kullanır

        return http.build();
    }
}
