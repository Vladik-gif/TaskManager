package com.vladik.rest.api.exception.handler;

import lombok.*;

@Builder
public record AppError(String error, String errorDescription) {}