package com.vladik.rest.api.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DeleteDto {
    private Boolean deleteId;
}