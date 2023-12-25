package com.vladik.rest.api.config;

import com.vladik.rest.store.entities.RoleEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/").permitAll()
                        .requestMatchers(HttpMethod.GET, "/user/**").hasAnyRole(
                                RoleEntity.ADMIN.name(),
                                RoleEntity.USER.name())
                        .requestMatchers(HttpMethod.POST, "/user/**").hasRole(RoleEntity.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, "/user/**").hasRole(RoleEntity.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, "/user/**").hasRole(RoleEntity.ADMIN.name())
                        .requestMatchers(HttpMethod.POST, "/todo/**").hasRole(RoleEntity.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, "/todo/**").hasRole(RoleEntity.ADMIN.name())
                        .anyRequest()
                        .authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("user"))
                .roles(RoleEntity.USER.name())
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles(RoleEntity.ADMIN.name())
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }
}
