package com.Project.ToDoList.mappers.impl;

import org.springframework.stereotype.Component;

import com.Project.ToDoList.domain.dto.TaskDto;
import com.Project.ToDoList.mappers.TaskMapper;
import com.Project.ToDoList.domain.entities.Task;

@Component
public class TaskMapperImpl implements TaskMapper {
    @Override
    public TaskDto toDto(Task task){
        
        return new TaskDto(task.getId(), task.getTitle(), task.getDescription(), task.getDueDate(), task.getPriority(), task.getStatus());
    }
    @Override
    public Task fromDto(TaskDto taskDto){
        return new Task(taskDto.id(), taskDto.title(), taskDto.description(), taskDto.dueDate(), taskDto.status(), null, taskDto.priority(), null, null);
    }
    
}
