package com.vladik.rest.api.factory;

import com.vladik.rest.api.dto.TaskDto;
import com.vladik.rest.api.dto.UserDto;
import com.vladik.rest.store.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserDtoFactory {
    private final TaskDtoFactory taskDtoFactory;
    public UserDto makeUserDto(UserEntity user){
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .todo(isNull(user))
                .build();
    }

    private List<TaskDto> isNull(UserEntity user){
        return Optional.ofNullable(user.getTodo())
                .map(todoList -> todoList.stream()
                        .map(taskDtoFactory::makeTaskDto)
                        .collect(Collectors.toList()))
                        .orElse(Collections.emptyList());
    }
}