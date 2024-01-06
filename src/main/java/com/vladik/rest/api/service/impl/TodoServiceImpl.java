package com.vladik.rest.api.service.impl;

import com.vladik.rest.api.factory.TodoDtoFactory;
import com.vladik.rest.api.service.TodoService;
import com.vladik.rest.api.service.serviceHelpers.ServiceExceptionHelpers;
import com.vladik.rest.store.entities.TodoEntity;
import com.vladik.rest.api.dto.TodoDto;
import com.vladik.rest.store.entities.UserEntity;
import com.vladik.rest.store.repository.TodoRepository;
import com.vladik.rest.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final TodoDtoFactory todoDtoFactory;
    private final UserRepository userRepository;
    private final ServiceExceptionHelpers serviceExceptionHelpers;

    public TodoDto createTodo(Long id,TodoEntity todoEntity){
        UserEntity userId = userRepository.getReferenceById(id);
        todoEntity.setUser(userId);

        serviceExceptionHelpers.serverHandlerNotFrondExceptionTitle(todoEntity);

        return todoDtoFactory.makeTodoDto(todoRepository.save(todoEntity));
    }

    public TodoDto updateTodo(Long id, TodoEntity todo){
        TodoEntity todoId = todoRepository.getReferenceById(id);

        serviceExceptionHelpers.serverHandlerIdException(id);

        TodoEntity saveTodo = TodoEntity.builder()
                .id(todoId.getId())
                .title(todoId.getTitle())
                .description(todoId.getDescription())
                .createDate(todoId.getCreateDate())
                .doneTask(todo.isDoneTask())
                .build();

        return todoDtoFactory.makeTodoDto(todoRepository.save(saveTodo));
    }

    @Override
    public List<TodoDto> getAll() {
        return todoRepository.findAll().stream()
                .map(todoDtoFactory::makeTodoDto)
                .collect(Collectors.toList());
    }
}