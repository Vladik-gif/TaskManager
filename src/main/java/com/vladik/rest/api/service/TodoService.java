package com.vladik.rest.api.service;

import com.vladik.rest.api.service.serviceHelpers.ServiceExceptionHelpers;
import com.vladik.rest.store.entities.TodoEntity;
import com.vladik.rest.store.entities.UserEntity;
import com.vladik.rest.store.model.TodoModel;
import com.vladik.rest.store.repository.TodoRepository;
import com.vladik.rest.store.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;
    private final ServiceExceptionHelpers serviceExceptionHelpers;

    public TodoService(TodoRepository todoRepository,
                       UserRepository userRepository,
                       ServiceExceptionHelpers serviceExceptionHelpers) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
        this.serviceExceptionHelpers = serviceExceptionHelpers;
    }

    public TodoModel createTodo(Long id, TodoEntity todoEntity){
        UserEntity user = userRepository.getReferenceById(id);

        serviceExceptionHelpers.serverHandlerNotFrondExceptionTitle(todoEntity);

        todoEntity.setUser(user);

        return TodoModel.todoEntityModel(todoRepository.save(todoEntity));
    }

    public TodoModel updateTodo(Long id, TodoEntity todo){
        TodoEntity todoEntity = todoRepository.getReferenceById(id);

        todoEntity.setTitle(todo.getTitle());

        return TodoModel.todoEntityModel(todoRepository.save(todo));
    }
}
