package com.vladik.rest.store.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotBlank(message = "Поле не повинно містить пусте значення, повторіть ще раз ")
    @Pattern(regexp = "^[A-Z][a-z]*(\\s(([a-z]{1,3})|(([a-z]+\\')?[A-Z][a-z]*)))*$")
    @Column(unique = true)
    private String username;
    @NotBlank(message = "Поле не повинно містить пусте значення, повторіть ще раз ")
    @Column(unique = true)
    private String password;
    @NotBlank(message = "Поле не повинно містить пусте значення, повторіть ще раз ")
    @Email(message = "Приклад: test@gmail")
    @Column(unique = true)
    private String email;
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<TodoEntity> todo;
}
