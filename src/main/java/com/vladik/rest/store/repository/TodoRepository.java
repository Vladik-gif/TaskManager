package com.vladik.rest.store.repository;

import com.vladik.rest.store.entities.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

    Optional<TodoEntity> findTodoEntityByAndTodo (String todo);
}
