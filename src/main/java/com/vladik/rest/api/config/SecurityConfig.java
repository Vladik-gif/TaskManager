package com.vladik.rest.api.config;

import com.vladik.rest.store.entities.RoleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final PasswordEncoderProvider passwordEncoderProvider;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/").permitAll()
                        .requestMatchers(HttpMethod.GET, "/user/**").hasAnyRole(
                                RoleEntity.ADMIN.name(),
                                RoleEntity.USER.name())
                        .requestMatchers(HttpMethod.GET, "/todo/**").hasAnyRole(
                                RoleEntity.ADMIN.name(),
                                RoleEntity.USER.name())
                        .requestMatchers(HttpMethod.POST, "/user/**").hasRole(RoleEntity.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, "/user/**").hasRole(RoleEntity.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, "/user/**").hasRole(RoleEntity.ADMIN.name())
                        .requestMatchers(HttpMethod.POST, "/todo/**").hasRole(RoleEntity.ADMIN.name())
                        .requestMatchers(HttpMethod.POST, "/сategory").hasRole(RoleEntity.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, "/todo/**").hasRole(RoleEntity.ADMIN.name())
                        .anyRequest()
                        .authenticated()
                )
                .oauth2Login(withDefaults())
                .formLogin(withDefaults())
                .httpBasic(withDefaults())
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoderProvider.passwordEncoder().encode("user"))
                .roles(RoleEntity.USER.name())
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoderProvider.passwordEncoder().encode("admin"))
                .roles(RoleEntity.ADMIN.name())
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}