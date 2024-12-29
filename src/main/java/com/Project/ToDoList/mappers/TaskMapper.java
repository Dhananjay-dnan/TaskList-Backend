package com.Project.ToDoList.mappers;

import com.Project.ToDoList.domain.dto.TaskDto;
import com.Project.ToDoList.domain.entities.Task;

public interface TaskMapper {
    Task fromDto(TaskDto taskDto);
    TaskDto toDto(Task task);
}
