package com.vladik.rest.api.factory;

import com.vladik.rest.api.dto.TaskDto;
import com.vladik.rest.store.entities.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskDtoFactory {

    public TaskDto makeTaskDto(TaskEntity todo){
        return TaskDto.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .createDate(todo.getCreateDate())
                .doneDateTask(todo.getDoneDateTask())
                .statusTask(todo.isStatusTask())
                .category(todo.getCategory())
                .build();
    }
}
