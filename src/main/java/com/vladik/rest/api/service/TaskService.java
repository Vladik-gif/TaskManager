package com.vladik.rest.api.service;

import com.vladik.rest.api.dto.TaskDto;
import com.vladik.rest.store.entities.TaskEntity;

import java.util.List;

public interface TaskService {
    TaskDto createTodo(Long id, TaskEntity taskEntity);
    TaskDto updateByIdTodo(Long id, TaskEntity todo);
    List<TaskDto> getAll();
    List<TaskDto> filterStatus(String status);
    List<TaskDto> filterCategory(String category);
}