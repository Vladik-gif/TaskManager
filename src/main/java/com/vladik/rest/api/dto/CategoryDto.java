package com.vladik.rest.api.dto;

import lombok.Builder;

@Builder
public record CategoryDto(Long id, String categoryName){}