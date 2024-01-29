package com.vladik.rest.security.models.request;

import com.vladik.rest.store.enums.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String lastname;
    private String username;
    private String email;
    private String password;
    private RoleEntity role;
}