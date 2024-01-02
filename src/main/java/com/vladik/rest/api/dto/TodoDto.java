package com.vladik.rest.api.dto;

import lombok.*;

@Builder
public record TodoDto(Long id, String title) {}