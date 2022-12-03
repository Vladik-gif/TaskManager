package com.vladik.rest.store.model;

import com.vladik.rest.store.entities.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class UserModel {

    private Long id;
    private String username;
    private String password;
    private String email;
    private List<TodoModel> todo;


    public static UserModel userModel(UserEntity user){
        UserModel userModel = new UserModel();

        userModel.setId(user.getId());
        userModel.setUsername(user.getUsername());
        userModel.setPassword(user.getPassword());
        userModel.setEmail(user.getEmail());
        userModel.setTodo(user.getTodo().stream().map(TodoModel::todoModel).collect(Collectors.toList()));

        return userModel;
    }

    public UserModel() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<TodoModel> getTodo() {
        return todo;
    }

    public void setTodo(List<TodoModel> todo) {
        this.todo = todo;
    }
}
