package br.com.postech.sevenfood.commons.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration  {

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeRequests()
                .requestMatchers("/v1/auth/signup").permitAll() // Permite acesso público ao endpoint de registro
                .requestMatchers("/v1/auth/login").permitAll() // Permite acesso público ao endpoint de login
                .anyRequest().authenticated();

        return http.build();// Configura o formulário de login padrão do Spring Security
    }
}
