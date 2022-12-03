package com.vladik.rest.store.model;

import com.vladik.rest.store.entities.TodoEntity;

public class TodoModel {

    private Long id;
    private String title;

    public static TodoModel todoModel(TodoEntity todo){
        TodoModel todoModel = new TodoModel();

        todoModel.setId(todo.getId());
        todoModel.setTitle(todo.getTitle());

        return todoModel;
    }

    public TodoModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
