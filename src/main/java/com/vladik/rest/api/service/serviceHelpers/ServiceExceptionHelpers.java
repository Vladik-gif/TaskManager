package com.vladik.rest.api.service.serviceHelpers;

import com.vladik.rest.api.exception.exception.BadRequestException;
import com.vladik.rest.api.exception.exception.NotFoundException;
import com.vladik.rest.store.entities.TodoEntity;
import com.vladik.rest.store.entities.UserEntity;
import com.vladik.rest.store.repository.TodoRepository;
import com.vladik.rest.store.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ServiceExceptionHelpers {

    private final UserRepository userRepository;
    private final TodoRepository todoRepository;

    public ServiceExceptionHelpers(UserRepository userRepository,
                                   TodoRepository todoRepository) {
        this.userRepository = userRepository;
        this.todoRepository = todoRepository;
    }

    public void serverHandlerIdException(Long id){
        userRepository.findById(id).orElseThrow(
                () -> new BadRequestException(
                        String.format("По такому id: \"%s\" не найдено пользователя", id))
        );
    }

    public void serverHandlerNotFoundException(UserEntity user){
        if(userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new NotFoundException(
                    String.format("Пользователь з таким именем: \"%s\" уже есть, повтори еще раз", user.getUsername())
            );
        }
    }

    public void serverHandlerNotFrondExceptionTitle(TodoEntity todo){
        if (todoRepository.findByTitle(todo.getTitle()).isPresent()){
            throw new NotFoundException(
                    String.format("Запись уже записанна: \"%s\"", todo.getTitle())
            );
        }
    }
}
