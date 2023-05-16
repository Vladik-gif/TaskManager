package com.vladik.rest.store.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotBlank
    @Pattern(regexp = "^[A-Z][a-z]*(\\s(([a-z]{1,3})|(([a-z]+\\')?[A-Z][a-z]*)))*$")
    @Column(unique = true)
    private String username;
    @NotBlank
    @Column(unique = true)
    private String password;
    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<TodoEntity> todo = new ArrayList<>();

    public UserEntity() {
    }

    public List<TodoEntity> getTodo() {
        return todo;
    }

    public void setTodo(List<TodoEntity> todo) {
        this.todo = todo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
