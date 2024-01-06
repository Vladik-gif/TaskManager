package com.vladik.rest.api.dto;

import com.vladik.rest.store.entities.CategoryEntity;
import lombok.*;

import java.time.LocalDateTime;

@Builder
public record TodoDto(Long id,
                      String title,
                      String description,
                      LocalDateTime createDate,
                      boolean doneTask,
                      CategoryEntity category
) {}