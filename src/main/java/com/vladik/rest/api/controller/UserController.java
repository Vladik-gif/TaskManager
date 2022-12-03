package com.vladik.rest.api.controller;

import com.vladik.rest.api.service.UserService;
import com.vladik.rest.store.entities.UserEntity;
import com.vladik.rest.store.model.DeleteModel;
import com.vladik.rest.store.model.UserModel;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    private static final String CREATE_USER = "/createUser";
    private static final String GET_ONE_USER = "/getUserOne";
    private static final String GET_ALL_USER = "/getUser";
    private static final String UPDATE_USER = "/updateUser/{id}";
    private static final String DELETE_USER = "/deleteUser";

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(CREATE_USER)
    public UserEntity create(@Valid @RequestBody UserEntity userEntity) {
        return userService.createUser(userEntity);
    }

    @GetMapping(GET_ONE_USER)
    public UserModel getOne(@RequestParam Long id){
        return userService.getOne(id);
    }

    @GetMapping(GET_ALL_USER)
    public List<UserEntity> getUser(){
        return userService.getUser();
    }

    @PutMapping(UPDATE_USER)
    public UserModel update(@PathVariable("id") Long id,
                            @Valid @RequestBody UserEntity username){
        return userService.update(id, username);
    }

    @DeleteMapping(DELETE_USER)
    public DeleteModel deleteUserId(@RequestParam Long id){
        return userService.deleteId(id);
    }
}
