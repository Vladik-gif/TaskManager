package com.vladik.rest.api.dto;

import lombok.*;
import java.util.List;

@Builder
public record UserDto (Long id, String username, String password, String email, List<TodoDto> todo) {}