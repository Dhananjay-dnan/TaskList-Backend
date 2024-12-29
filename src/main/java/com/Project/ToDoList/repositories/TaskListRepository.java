package com.Project.ToDoList.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Project.ToDoList.domain.entities.TaskList;

public interface TaskListRepository extends JpaRepository<TaskList, UUID> {
    
}
