package com.Project.ToDoList.mappers;

import com.Project.ToDoList.domain.dto.TaskListDto;
import com.Project.ToDoList.domain.entities.TaskList;

public interface TaskListMapper {
    
   TaskList fromDto(TaskListDto taskListDto);
    TaskListDto toDto(TaskList taskList);
}
