package com.vladik.rest.api.service.impl;

import com.vladik.rest.api.factory.TaskDtoFactory;
import com.vladik.rest.api.service.TaskService;
import com.vladik.rest.api.service.serviceHelpers.ServiceExceptionHelpers;
import com.vladik.rest.store.entities.TaskEntity;
import com.vladik.rest.api.dto.TaskDto;
import com.vladik.rest.store.entities.UserEntity;
import com.vladik.rest.store.repository.TaskRepository;
import com.vladik.rest.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskDtoFactory taskDtoFactory;
    private final UserRepository userRepository;
    private final ServiceExceptionHelpers serviceExceptionHelpers;

    public TaskDto createTodo(Long id, TaskEntity taskEntity){
        UserEntity userId = userRepository.getReferenceById(id);
        taskEntity.setUser(userId);

        serviceExceptionHelpers.serverHandlerNotFrondExceptionTitle(taskEntity);

        return taskDtoFactory.makeTaskDto(taskRepository.save(taskEntity));
    }

    public TaskDto updateByIdTodo(Long id, TaskEntity todo){
        TaskEntity todoId = taskRepository.getReferenceById(id);

        serviceExceptionHelpers.serverHandlerIdException(id);

        TaskEntity saveTodo = TaskEntity.builder()
                .id(todoId.getId())
                .title(todoId.getTitle())
                .description(todoId.getDescription())
                .createDate(todoId.getCreateDate())
                .doneDateTask(todoId.getDoneDateTask())
                .statusTask(todoId.getStatusTask())
                .build();

        return taskDtoFactory.makeTaskDto(taskRepository.save(saveTodo));
    }

    @Override
    public List<TaskDto> getAll() {
        return findAllTask();
    }

    @Override
    public List<TaskDto> filterStatus(String status) {
        return findAllTask().stream()
                .filter(taskDto -> taskDto.statusTask().name().contains(status))
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> filterCategory(String category) {
        return findAllTask().stream()
                .filter(taskDto -> taskDto.category().getCategoryName().contains(category))
                .collect(Collectors.toList());
    }

    private List<TaskDto> findAllTask(){
        return taskRepository.findAll()
                .stream()
                .map(taskDtoFactory::makeTaskDto).toList();
    }
}