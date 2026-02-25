package com.movieflix.demo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {


    // private final SecurityFilter securityFilter;

    @Bean
//Essa anotação indica que o méto-do retorna um bean gerenciado pelo Spring, que pode ser injetado em outras partes da aplicação.
    public SecurityFilterChain securityFilterChain(HttpSecurity http, SecurityFilter securityFilter) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)//Desabilita a proteção contra CSRF (Cross-Site Request Forgery), o que pode ser necessário para APIs RESTful.
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))//Configura o gerenciamento de sessão para ser sem estado, o que é comum em APIs RESTful.
                .authorizeHttpRequests(authorizes -> authorizes
                        .requestMatchers(HttpMethod.POST, "/movieflix/auth/register").permitAll() //Permite que qualquer pessoa acesse o endpoint de registro sem autenticação.
                        .requestMatchers(HttpMethod.POST, "/movieflix/auth/login").permitAll() //Permite que qualquer pessoa acesse o endpoint de login sem autenticação.
                        .anyRequest().authenticated())//Exige autenticação para todas as outras requisições.
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
