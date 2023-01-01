package com.vladik.rest.api.config;

import com.vladik.rest.store.entities.RoleEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .mvcMatchers("/").permitAll()
                .mvcMatchers(HttpMethod.GET, "/user/**").hasAnyRole(
                        RoleEntity.ADMIN.name(),
                        RoleEntity.USER.name())
                .mvcMatchers(HttpMethod.POST, "/user/**").hasRole(RoleEntity.ADMIN.name())
                .mvcMatchers(HttpMethod.PUT, "/user/**").hasRole(RoleEntity.ADMIN.name())
                .mvcMatchers(HttpMethod.DELETE, "/user/**").hasRole(RoleEntity.ADMIN.name())
                .mvcMatchers(HttpMethod.POST, "/todo/**").hasRole(RoleEntity.ADMIN.name())
                .mvcMatchers(HttpMethod.PUT, "/todo/**").hasRole(RoleEntity.ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.builder()
                        .username("admin")
                        .password(passwordEncoder().encode("admin"))
                        .roles("ADMIN")
                        .build(),

                User.builder()
                        .username("user")
                        .password(passwordEncoder().encode("user"))
                        .roles("USER")
                        .build()
        );
    }

    @Bean
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }
}
