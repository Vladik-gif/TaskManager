package com.vladik.rest.api.controller;

import com.vladik.rest.api.service.TaskService;
import com.vladik.rest.store.entities.TaskEntity;
import com.vladik.rest.api.dto.TaskDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private static final String CREATE_TODO = "/create/{user_id}";
    private static final String UPDATE_TODO = "/update/{id}";
    private static final String GETS_TODOS = "/getAll";

    @PostMapping(CREATE_TODO)
    public TaskDto createTodo(@PathVariable(name = "user_id") Long id,
                              @Valid @RequestBody TaskEntity todo){
        return taskService.createTodo(id, todo);
    }

    @PatchMapping(UPDATE_TODO)
    public TaskDto update(@PathVariable Long id,
                          @Valid @RequestBody TaskEntity todo){
        return taskService.updateTodo(id, todo);
    }

    @GetMapping(GETS_TODOS)
    public List<TaskDto> getTodos(){
        return taskService.getAll();
    }
}