package com.vladik.rest.api.service.impl;

import com.vladik.rest.api.dto.TodoDto;
import com.vladik.rest.store.entities.TodoEntity;

public interface TodoServiceImpl {
    TodoDto createTodo(Long id, TodoEntity todoEntity);
    TodoDto updateTodo(Long id, TodoEntity todo);
}
