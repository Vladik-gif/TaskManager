package com.vladik.rest.api.service.serviceHelpers;

import com.vladik.rest.api.exception.BadRequestException;
import com.vladik.rest.api.exception.NotFoundException;
import com.vladik.rest.store.entities.TaskEntity;
import com.vladik.rest.store.entities.UserEntity;
import com.vladik.rest.store.repository.TaskRepository;
import com.vladik.rest.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceExceptionHelpers {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

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

    public void serverHandlerNotFrondExceptionTitle(TaskEntity todo){
        if (taskRepository.findByTitle(todo.getTitle()).isPresent()){
            throw new NotFoundException(
                    String.format("Задача вже є: %s ", todo.getTitle())
            );
        }
    }
}