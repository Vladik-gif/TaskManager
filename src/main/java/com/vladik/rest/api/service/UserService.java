package com.vladik.rest.api.service;

import com.vladik.rest.api.service.serviceHelpers.ServiceExceptionHelpers;
import com.vladik.rest.store.entities.UserEntity;
import com.vladik.rest.store.model.DeleteModel;
import com.vladik.rest.store.model.UserModel;
import com.vladik.rest.store.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ServiceExceptionHelpers serviceExceptionHelpers;

    public UserService(UserRepository userRepository,
                       ServiceExceptionHelpers serviceExceptionHelpers) {
        this.userRepository = userRepository;
        this.serviceExceptionHelpers = serviceExceptionHelpers;
    }

    public UserEntity createUser(UserEntity userEntity) {
        serviceExceptionHelpers.serverHandlerNotFoundException(userEntity);

        return userRepository.save(userEntity);
    }

    public UserModel getOne(Long id) {
        UserEntity user = userRepository.getReferenceById(id);
        serviceExceptionHelpers.serverHandlerIdException(id);

        return UserModel.userModel(user);
    }

    public List<UserEntity> getUser() {
        return userRepository.findAll();
    }

    public UserModel update(Long id, UserEntity user) {
        UserEntity userEntity = userRepository.getReferenceById(id);

        serviceExceptionHelpers.serverHandlerIdException(id);

        userEntity.setUsername(user.getUsername());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());

        return UserModel.userModel(userEntity);
    }

    public DeleteModel deleteId(Long id){
        serviceExceptionHelpers.serverHandlerIdException(id);

        userRepository.deleteById(id);

        return DeleteModel.deleteModel(true);
    }
}