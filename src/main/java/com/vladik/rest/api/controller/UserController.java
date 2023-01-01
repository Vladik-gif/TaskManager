package com.vladik.rest.api.controller;

import com.vladik.rest.api.service.UserService;
import com.vladik.rest.store.entities.UserEntity;
import com.vladik.rest.api.dto.DeleteDto;
import com.vladik.rest.api.dto.UserDto;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private static final String CREATE_USER = "/create";
    private static final String GET_ONE_USER = "/getOne";
    private static final String GET_ALL_USER = "/getUser";
    private static final String UPDATE_USER = "/update";
    private static final String DELETE_USER = "/delete";

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(CREATE_USER)
    public UserDto create(@Valid @RequestBody UserEntity userEntity) {
        return userService.createUser(userEntity);
    }

    @GetMapping(GET_ONE_USER)
    public UserDto getOne(@RequestParam Long id){
        return userService.getOne(id);
    }

    @GetMapping(GET_ALL_USER)
    public List<UserDto> getUser(){
        return userService.getUser();
    }

    @PutMapping(UPDATE_USER)
    public UserDto update(@RequestParam(required = false) Long id,
                          @Valid @RequestBody UserEntity username){
        return userService.update(id,username);
    }

    @DeleteMapping(DELETE_USER)
    public DeleteDto deleteUserId(@RequestParam Long id){
        return userService.deleteId(id);
    }
}
