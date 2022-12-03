package com.vladik.rest.api.service.serviceHelpers;

import com.vladik.rest.api.exception.exception.BadRequestException;
import com.vladik.rest.api.exception.exception.NotFoundException;
import com.vladik.rest.store.entities.UserEntity;
import com.vladik.rest.store.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ServiceExceptionHelpers {

    private final UserRepository userRepository;

    public ServiceExceptionHelpers(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void serverHandlerIdException(Long id){
        userRepository.findById(id).orElseThrow(
                () -> new BadRequestException("По такому id: " + id + " не найдено пользователя"));
    }

    public void serverHandlerNotFoundException(UserEntity user){
        if(userRepository.findUserEntityByAndUsername(user.getUsername()).isPresent()) {
            throw new NotFoundException
                    ("Пользователь з таким именем: " + user.getUsername() + " уже есть, повтори еще раз");
        }
    }

}
