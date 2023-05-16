package com.vladik.rest.api.config;

import com.vladik.rest.store.entities.RoleEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.core.userdetails.User.builder;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf()
                .disable()
                .cors()
                .and()
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/").permitAll()
                        .requestMatchers(HttpMethod.GET, "/user/**").hasAnyRole(
                                RoleEntity.ADMIN.name(), RoleEntity.USER.name())
                        .requestMatchers(HttpMethod.POST, "/user/**").hasRole(RoleEntity.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, "/user/**").hasRole(RoleEntity.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, "/user/**").hasRole(RoleEntity.ADMIN.name())
                        .requestMatchers(HttpMethod.POST, "/todo/**").hasRole(RoleEntity.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, "/todo/**").hasRole(RoleEntity.ADMIN.name())
                        .anyRequest()
                        .authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {

        UserDetails user = builder()
                .username("user")
                .password(passwordEncoder().encode("user"))
                .roles(String.valueOf(RoleEntity.USER))
                .build();

        UserDetails admin = builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles(String.valueOf(RoleEntity.ADMIN))
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
