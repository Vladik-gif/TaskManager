package com.vladik.rest.api.service;

import com.vladik.rest.api.factory.DeleteDtoFactory;
import com.vladik.rest.api.factory.UserDtoFactory;
import com.vladik.rest.api.service.serviceHelpers.ServiceExceptionHelpers;
import com.vladik.rest.store.entities.UserEntity;
import com.vladik.rest.api.dto.DeleteDto;
import com.vladik.rest.api.dto.UserDto;
import com.vladik.rest.store.repository.UserRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final ServiceExceptionHelpers serviceExceptionHelpers;
    private final DeleteDtoFactory deleteDtoFactory;
    private final UserDtoFactory userDtoFactory;

    public UserService(UserRepository userRepository,
                       ServiceExceptionHelpers serviceExceptionHelpers,
                       DeleteDtoFactory deleteDtoFactory,
                       UserDtoFactory userDtoFactory) {
        this.userRepository = userRepository;
        this.serviceExceptionHelpers = serviceExceptionHelpers;
        this.deleteDtoFactory = deleteDtoFactory;
        this.userDtoFactory = userDtoFactory;
    }

    public UserDto createUser(UserEntity userEntity) {
        serviceExceptionHelpers.serverHandlerNotFoundException(userEntity);

        log.debug("Create user: " + userEntity);

        return userDtoFactory.makeUserDto(userRepository.save(userEntity));
    }

    public UserDto getOne(Long id) {
        UserEntity user = userRepository.getReferenceById(id);
        serviceExceptionHelpers.serverHandlerIdException(id);

        log.debug("Get user: " + user + " id " + id);

        return userDtoFactory.makeUserDto(user);
    }

    public List<UserDto> getUser() {
        log.debug("Get users");

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

        log.debug("Update user: "  + userEntity);

        return userDtoFactory.makeUserDto(userSave);
    }

    public DeleteDto deleteId(Long id){
        serviceExceptionHelpers.serverHandlerIdException(id);

        userRepository.deleteById(id);

        log.debug("Delete user: " + id);

        return deleteDtoFactory.makeDeleteDto(true);
    }
}