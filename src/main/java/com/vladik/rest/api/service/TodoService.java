package com.vladik.rest.api.service;

import com.vladik.rest.api.exception.exception.NotFoundException;
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

    public TodoService(TodoRepository todoRepository,
                       UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }


    public TodoModel createTodo(Long id, TodoEntity todoEntity){
        UserEntity user = userRepository.getReferenceById(id);

        if (todoRepository.findTodoEntityByAndTodo(todoEntity.getTitle()).isPresent()){
            throw new NotFoundException("Запись уже записанна :" + todoEntity.getTitle());
        }

        todoEntity.setUser(user);

        return TodoModel.todoModel(todoRepository.save(todoEntity));
    }

    public TodoModel updateTodo(Long id, TodoEntity todo){
        TodoEntity todoEntity = todoRepository.getReferenceById(id);

        todoEntity.setTitle(todo.getTitle());

        return TodoModel.todoModel(todoRepository.save(todo));
    }
}
