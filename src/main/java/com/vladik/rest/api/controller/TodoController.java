package com.vladik.rest.api.controller;

import com.vladik.rest.api.service.TodoService;
import com.vladik.rest.store.entities.TodoEntity;

import com.vladik.rest.api.dto.TodoDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    private static final String CREATE_TODO = "/create/{user_id}";
    private static final String UPDATE_TODO = "/update";

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping(CREATE_TODO)
    public TodoDto createTodo(@PathVariable(name = "user_id") Long id,
                              @Valid @RequestBody TodoEntity todo){
        return todoService.createTodo(id, todo);
    }

    @PatchMapping(UPDATE_TODO)
    public TodoDto update(@RequestParam Long id,
                          @Valid @RequestBody TodoEntity todo){
        return todoService.updateTodo(id, todo);
    }
}
