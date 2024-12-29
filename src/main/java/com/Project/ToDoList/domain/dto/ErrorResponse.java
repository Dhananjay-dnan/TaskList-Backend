package com.Project.ToDoList.domain.dto;

public record ErrorResponse(int status,
String message,
String details) {
    
}
