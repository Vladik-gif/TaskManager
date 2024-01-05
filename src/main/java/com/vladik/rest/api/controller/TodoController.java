package com.vladik.rest.api.controller;

import com.vladik.rest.api.service.TodoService;
import com.vladik.rest.store.entities.TodoEntity;
import com.vladik.rest.api.dto.TodoDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;
    private static final String CREATE_TODO = "/create/{user_id}";
    private static final String UPDATE_TODO = "/update/{id}";
    private static final String GETS_TODOS = "/getAll";

    @PostMapping(CREATE_TODO)
    public TodoDto createTodo(@PathVariable(name = "user_id") Long id,
                              @Valid @RequestBody TodoEntity todo){
        return todoService.createTodo(id, todo);
    }

    @PatchMapping(UPDATE_TODO)
    public TodoDto update(@PathVariable Long id,
                          @Valid @RequestBody TodoEntity todo){
        return todoService.updateTodo(id, todo);
    }

    @GetMapping(GETS_TODOS)
    public List<TodoDto> getTodos(){
        return todoService.getAll();
    }
}