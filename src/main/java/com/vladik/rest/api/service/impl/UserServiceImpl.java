package com.vladik.rest.api.service.impl;

import com.vladik.rest.api.dto.DeleteDto;
import com.vladik.rest.api.dto.UserDto;
import com.vladik.rest.store.entities.UserEntity;

import java.util.List;

public interface UserServiceImpl {

    UserDto createUser(UserEntity userEntity);
    UserDto getOne(Long id);
    List<UserDto> getUser();
    UserDto update(Long id, UserEntity user);
    DeleteDto deleteId(Long id);
}