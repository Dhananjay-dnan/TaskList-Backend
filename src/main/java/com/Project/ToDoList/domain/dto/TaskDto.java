package com.Project.ToDoList.domain.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.Project.ToDoList.domain.entities.TaskPriority;
import com.Project.ToDoList.domain.entities.TaskStatus;

public record TaskDto(
    UUID id,
    String title,
    String description,
    LocalDateTime dueDate,
    TaskPriority priority,
    TaskStatus status
)
{}
