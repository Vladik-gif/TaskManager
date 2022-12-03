package com.vladik.rest.api.controller;

import com.vladik.rest.api.service.TodoService;
import com.vladik.rest.store.entities.TodoEntity;

import com.vladik.rest.store.model.TodoModel;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    private static final String CREATE_TODO = "/createTodo";
    private static final String UPDATE_TODO = "/updateTodo";

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping(CREATE_TODO)
    public TodoModel createTodo(@RequestParam Long id,
                                @Valid  @RequestBody TodoEntity todo){
        return todoService.createTodo(id, todo);
    }

    @PatchMapping(UPDATE_TODO)
    public TodoModel update(@RequestParam Long id,
                             @Valid @RequestBody TodoEntity todo){
        return todoService.updateTodo(id, todo);
    }
}
