package com.vladik.rest.api.dto;

import com.vladik.rest.store.entities.CategoryEntity;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record TaskDto(Long id,
                      String title,
                      String description,
                      LocalDateTime createDate,
                      LocalDate doneDateTask,
                      boolean statusTask,
                      CategoryEntity category
) {}