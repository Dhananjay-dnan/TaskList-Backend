package com.Project.ToDoList.domain.dto;

import java.util.*;


public record TaskListDto(
        UUID id,
        String title,
        String description,
        Integer count,
        Double progress,
        List<TaskDto> tasks
) {
    
}
