package com.vladik.rest.api.factory;

import com.vladik.rest.api.dto.TodoDto;
import com.vladik.rest.store.entities.CategoryEntity;
import com.vladik.rest.store.entities.TodoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class TodoDtoFactory {

    public TodoDto makeTodoDto(TodoEntity todo){
        return TodoDto.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .createDate(todo.getCreateDate())
                .doneTask(todo.isDoneTask())
                .category(todo.getCategory())
                .build();
    }
}
