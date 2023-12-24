package com.vladik.rest.api.exception.extitiException;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AppError {
    private int status;
    private String message;
}