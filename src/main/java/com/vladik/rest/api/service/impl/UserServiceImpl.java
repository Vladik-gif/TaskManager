package com.vladik.rest.api.service.impl;

import com.vladik.rest.api.dto.DeleteDto;
import com.vladik.rest.api.dto.UserDto;
import com.vladik.rest.api.factory.DeleteDtoFactory;
import com.vladik.rest.api.factory.UserDtoFactory;
import com.vladik.rest.api.service.UserService;
import com.vladik.rest.api.service.serviceHelpers.ServiceExceptionHelpers;
import com.vladik.rest.store.entities.UserEntity;
import com.vladik.rest.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ServiceExceptionHelpers serviceExceptionHelpers;
    private final DeleteDtoFactory deleteDtoFactory;
    private final UserDtoFactory userDtoFactory;

    public UserDto createUser(UserEntity userEntity) {
        serviceExceptionHelpers.serverHandlerNotFoundException(userEntity);

        return userDtoFactory.makeUserDto(userRepository.save(userEntity));
    }

    public UserDto getOne(Long id) {
        UserEntity user = userRepository.getReferenceById(id);
        serviceExceptionHelpers.serverHandlerIdException(id);

        return userDtoFactory.makeUserDto(user);
    }

    public List<UserDto> getUsers() {
        return userRepository.findAll().stream()
                .map(userDtoFactory::makeUserDto)
                .collect(Collectors.toList());
    }

    public UserDto update(Long id, UserEntity user) {
        UserEntity userEntity = userRepository.getReferenceById(id);

        serviceExceptionHelpers.serverHandlerIdException(id);

        userEntity.setUsername(user.getUsername());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());

        UserEntity userSave = userRepository.save(userEntity);

        return userDtoFactory.makeUserDto(userSave);
    }

    public DeleteDto deleteId(Long id){
        serviceExceptionHelpers.serverHandlerIdException(id);

        userRepository.deleteById(id);

        return deleteDtoFactory.makeDeleteDto(true);
    }

    @Override
    public List<UserDto> filterUsername(String filter) {
        List<UserDto> users = userRepository.findAll().stream()
                .map(userDtoFactory::makeUserDto)
                .toList();

        return users.stream()
                .filter(userDto -> userDto.username().contains(filter))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }
}