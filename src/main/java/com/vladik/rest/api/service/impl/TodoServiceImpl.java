package com.vladik.rest.api.service.impl;

import com.vladik.rest.api.factory.TodoDtoFactory;
import com.vladik.rest.api.service.TodoService;
import com.vladik.rest.api.service.serviceHelpers.ServiceExceptionHelpers;
import com.vladik.rest.store.entities.TodoEntity;
import com.vladik.rest.api.dto.TodoDto;
import com.vladik.rest.store.entities.UserEntity;
import com.vladik.rest.store.repository.TodoRepository;
import com.vladik.rest.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final TodoDtoFactory todoDtoFactory;
    private final UserRepository userRepository;
    private final ServiceExceptionHelpers serviceExceptionHelpers;

    public TodoDto createTodo(Long id,TodoEntity todoEntity){
        UserEntity userId = userRepository.getReferenceById(id);
        todoEntity.setUser(userId);

        serviceExceptionHelpers.serverHandlerNotFrondExceptionTitle(todoEntity);

        return todoDtoFactory.makeTodoDto(todoRepository.save(todoEntity));
    }

    public TodoDto updateTodo(Long id, TodoEntity todo){
        TodoEntity todoEntity = todoRepository.getReferenceById(id);

        serviceExceptionHelpers.serverHandlerIdException(id);

        todoEntity.setTitle(todo.getTitle());

        return todoDtoFactory.makeTodoDto(todoRepository.save(todo));
    }
}