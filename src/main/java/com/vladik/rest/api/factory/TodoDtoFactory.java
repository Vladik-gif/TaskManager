package com.vladik.rest.api.factory;

import com.vladik.rest.api.dto.TodoDto;
import com.vladik.rest.store.entities.TodoEntity;
import org.springframework.stereotype.Component;

@Component
public class TodoDtoFactory {

    public TodoDto makeTodoDto(TodoEntity todo){
        TodoDto todoModel = new TodoDto();

        todoModel.setId(todo.getId());
        todoModel.setTitle(todo.getTitle());

        return todoModel;
    }
}
