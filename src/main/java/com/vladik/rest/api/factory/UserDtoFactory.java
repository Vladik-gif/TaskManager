package com.vladik.rest.api.factory;

import com.vladik.rest.api.dto.UserDto;
import com.vladik.rest.store.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserDtoFactory {
    private final TodoDtoFactory todoDtoFactory;
    public UserDto makeUserDto(UserEntity user){
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .todo(user.getTodo().stream()
                        .map(todoDtoFactory::makeTodoDto)
                        .collect(Collectors.toList())
                )
                .build();
    }
}