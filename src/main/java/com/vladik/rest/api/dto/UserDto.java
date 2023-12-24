package com.vladik.rest.api.dto;

import lombok.*;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String email;
    private List<TodoDto> todo;
}