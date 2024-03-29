package com.vladik.rest.api.service;

import com.vladik.rest.api.dto.DeleteDto;
import com.vladik.rest.api.dto.UserDto;
import com.vladik.rest.store.entities.UserEntity;

import java.util.List;

public interface UserService {
    UserDto createUser(UserEntity userEntity);
    UserDto getByIdUser(Long id);
    List<UserDto> getUsers();
    UserDto updateUserById(Long id, UserEntity user);
    DeleteDto deleteId(Long id);
    List<UserDto> filterUsername(String filter);
    void deleteAll();
}
