package com.vladik.rest.api.controller;

import com.vladik.rest.api.dto.DeleteDto;
import com.vladik.rest.api.dto.UserDto;
import com.vladik.rest.api.service.UserService;
import com.vladik.rest.store.entities.UserEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private static final String CREATE_USER = "/create";
    private static final String GET_ONE_USER = "/{id}";
    private static final String GET_ALL_USER = "/gets";
    private static final String UPDATE_USER = "/update/{id}";
    private static final String FILTER_USERNAME = "/filter/{username}";
    private static final String DELETE_USER = "/delete/{id}";
    private static final String DELETE_USER_ALL = "/deleteAll";

    @PostMapping(CREATE_USER)
    public UserDto create(@Valid @RequestBody UserEntity userEntity) {
        return userService.createUser(userEntity);
    }

    @GetMapping(GET_ONE_USER)
    public UserDto getOne(@PathVariable Long id){
        return userService.getByIdUser(id);
    }

    @GetMapping(GET_ALL_USER)
    public List<UserDto> getUser(){
        return userService.getUsers();
    }

    @PutMapping(UPDATE_USER)
    public UserDto update(@PathVariable Long id,
                          @Valid @RequestBody UserEntity username){
        return userService.updateUserById(id,username);
    }
    @GetMapping(FILTER_USERNAME)
    public List<UserDto> filterUsername(@PathVariable String username){
        return userService.filterUsername(username);
    }
    @DeleteMapping(DELETE_USER)
    public DeleteDto deleteUserId(@PathVariable Long id){
        return userService.deleteId(id);
    }

    @DeleteMapping(DELETE_USER_ALL)
    public String deleteAllUser(){
        userService.deleteAll();
        return "Users deleteAll";
    }
}