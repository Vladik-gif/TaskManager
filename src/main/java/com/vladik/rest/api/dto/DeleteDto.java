package com.vladik.rest.api.dto;

import lombok.*;

@Builder
public record DeleteDto(Boolean deleteId) { }