package com.vladik.rest.api.service;

import com.vladik.rest.api.dto.TodoDto;
import com.vladik.rest.store.entities.TodoEntity;

import java.util.List;

public interface TodoService {
    TodoDto createTodo(Long id, TodoEntity todoEntity);
    TodoDto updateTodo(Long id, TodoEntity todo);
    List<TodoDto> getAll();
}
