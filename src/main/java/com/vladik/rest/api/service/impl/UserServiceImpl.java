package com.vladik.rest.api.service.impl;

import com.vladik.rest.api.dto.DeleteDto;
import com.vladik.rest.api.dto.UserDto;
import com.vladik.rest.api.factory.DeleteDtoFactory;
import com.vladik.rest.api.factory.UserDtoFactory;
import com.vladik.rest.api.service.UserService;
import com.vladik.rest.api.service.serviceHelpers.ServiceExceptionHelpers;
import com.vladik.rest.store.entities.UserEntity;
import com.vladik.rest.store.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "UserCache")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ServiceExceptionHelpers serviceExceptionHelpers;
    private final DeleteDtoFactory deleteDtoFactory;
    private final UserDtoFactory userDtoFactory;

    @Transactional
    @CacheEvict(value = "users", allEntries = true)
    public UserDto createUser(UserEntity userEntity) {
        serviceExceptionHelpers.serverHandlerNotFoundException(userEntity);

        return userDtoFactory.makeUserDto(userRepository.save(userEntity));
    }
    @Transactional
    @Cacheable(cacheNames = "user", key = "#id", unless = "#result == null")
    public UserDto getByIdUser(Long id) {
        UserEntity user = userRepository.getReferenceById(id);
        serviceExceptionHelpers.serverHandlerIdException(id);

        return userDtoFactory.makeUserDto(user);
    }
    @Transactional
    @Cacheable(cacheNames = "users")
    public List<UserDto> getUsers() {
        return findAllUser();
    }
    @Transactional
    @CacheEvict(value = "users", allEntries = true)
    public UserDto updateUserById(Long id, UserEntity user) {
        UserEntity userEntity = userRepository.getReferenceById(id);

        serviceExceptionHelpers.serverHandlerIdException(id);

        userEntity.setUsername(user.getUsername());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());

        UserEntity userSave = userRepository.save(userEntity);

        return userDtoFactory.makeUserDto(userSave);
    }
    @Transactional
    @CacheEvict(value = "users", allEntries = true)
    public DeleteDto deleteId(Long id){
        serviceExceptionHelpers.serverHandlerIdException(id);

        userRepository.deleteById(id);

        return deleteDtoFactory.makeDeleteDto(true);
    }

    @Override
    public List<UserDto> filterUsername(String username) {
        return findAllUser().stream()
                .filter(userDto -> userDto.username().contains(username))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    private List<UserDto> findAllUser(){
        return userRepository.findAll().stream()
                .map(userDtoFactory::makeUserDto)
                .toList();
    }
}