package com.vladik.rest.api.service;

import com.vladik.rest.api.factory.TodoDtoFactory;
import com.vladik.rest.api.service.impl.TodoServiceImpl;
import com.vladik.rest.api.service.serviceHelpers.ServiceExceptionHelpers;
import com.vladik.rest.store.entities.TodoEntity;
import com.vladik.rest.api.dto.TodoDto;
import com.vladik.rest.store.entities.UserEntity;
import com.vladik.rest.store.repository.TodoRepository;
import com.vladik.rest.store.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class TodoService implements TodoServiceImpl {

    private final TodoRepository todoRepository;
    private final TodoDtoFactory todoDtoFactory;
    private final UserRepository userRepository;
    private final ServiceExceptionHelpers serviceExceptionHelpers;

    public TodoService(TodoRepository todoRepository,
                       TodoDtoFactory todoDtoFactory,
                       UserRepository userRepository,
                       ServiceExceptionHelpers serviceExceptionHelpers) {
        this.todoRepository = todoRepository;
        this.todoDtoFactory = todoDtoFactory;
        this.userRepository = userRepository;
        this.serviceExceptionHelpers = serviceExceptionHelpers;
    }

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