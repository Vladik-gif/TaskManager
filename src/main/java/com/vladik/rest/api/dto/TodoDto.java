package com.vladik.rest.api.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
public record TodoDto(Long id, String title, String description, LocalDateTime createDate) {}