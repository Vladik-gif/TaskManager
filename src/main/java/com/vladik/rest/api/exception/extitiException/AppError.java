package com.vladik.rest.api.exception.extitiException;

import lombok.*;

@Builder
public record AppError(int status, String message) {}