package com.vladik.rest.api.service.serviceHelpers;

import com.vladik.rest.api.exception.exception.BadRequestException;
import com.vladik.rest.api.exception.exception.NotFoundException;
import com.vladik.rest.store.entities.TodoEntity;
import com.vladik.rest.store.entities.UserEntity;
import com.vladik.rest.store.repository.TodoRepository;
import com.vladik.rest.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceExceptionHelpers {

    private final UserRepository userRepository;
    private final TodoRepository todoRepository;

    public void serverHandlerIdException(Long id){
        userRepository.findById(id).orElseThrow(
                () -> new BadRequestException(
                        String.format("По цьому id: %s не знаходить користувача", id))
        );
    }

    public void serverHandlerNotFoundException(UserEntity user){
        if(userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new NotFoundException(
                    String.format("Корисувач з цим ім'ям %s вже є, спробуйте ще раз", user.getUsername())
            );
        }
    }

    public void serverHandlerNotFrondExceptionTitle(TodoEntity todo){
        if (todoRepository.findByTitle(todo.getTitle()).isPresent()){
            throw new NotFoundException(
                    String.format("Задача вже є: %s ", todo.getTitle())
            );
        }
    }
}