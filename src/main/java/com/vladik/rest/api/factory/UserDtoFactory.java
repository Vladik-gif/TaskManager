package com.vladik.rest.api.factory;

import com.vladik.rest.api.dto.UserDto;
import com.vladik.rest.store.entities.UserEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserDtoFactory {

    private final TodoDtoFactory todoDtoFactory;

    public UserDtoFactory(TodoDtoFactory todoDtoFactory) {
        this.todoDtoFactory = todoDtoFactory;
    }

    public UserDto makeUserDto(UserEntity user){
        UserDto userModel = new UserDto();

        userModel.setId(user.getId());
        userModel.setUsername(user.getUsername());
        userModel.setPassword(user.getPassword());
        userModel.setEmail(user.getEmail());
        userModel.setTodo(user.getTodo().stream()
                .map(todoDtoFactory::makeTodoDto)
                .collect(Collectors.toList())
        );
        return userModel;
    }
}
