package com.vladik.rest.store.entities;

import com.vladik.rest.store.enums.StatusEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "task")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    private LocalDateTime createDate = LocalDateTime.now();
    private LocalDate doneDateTask;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @Enumerated(value = EnumType.STRING)
    private StatusEntity statusTask;
    @ManyToOne
    private CategoryEntity category;
}