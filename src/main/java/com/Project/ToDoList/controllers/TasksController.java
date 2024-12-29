package com.Project.ToDoList.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Project.ToDoList.domain.dto.TaskDto;
import com.Project.ToDoList.domain.entities.Task;
import com.Project.ToDoList.mappers.TaskMapper;
import com.Project.ToDoList.services.TaskService;

@RestController
@RequestMapping(path="/task-lists/{task_list_id}/tasks")
public class TasksController {
    private final TaskMapper taskMapper;
    private final TaskService taskService;
    
    public TasksController(TaskMapper taskMapper, TaskService taskService)
    {
        this.taskMapper = taskMapper;
        this.taskService = taskService;
    }

    @GetMapping
    List<TaskDto> listTasks(@PathVariable("task_list_id") UUID taskListId)
    {
        return taskService.listTasks(taskListId).stream().map(taskMapper::toDto).toList();
        
    }

    @GetMapping(path="/{task_id}")
    Optional<TaskDto> getTask(@PathVariable("task_list_id") UUID taskListId, @PathVariable("task_id") UUID taskId)
    {
        return taskService.getTask(taskListId, taskId).map(taskMapper::toDto);
    }

    @PutMapping(path="/{task_id}")
    TaskDto updateTask(@PathVariable("task_list_id") UUID taskListId, @PathVariable("task_id") UUID taskId, @RequestBody TaskDto taskDto)
    {
        Task updatedTask = taskService.updateTask(taskListId, taskId, taskMapper.fromDto(taskDto));
        return taskMapper.toDto(updatedTask);
    }


    @PostMapping
    TaskDto createTask(@PathVariable("task_list_id") UUID taskListId, @RequestBody TaskDto taskDto)
    {
        Task createdTask = taskService.createTask(taskListId, taskMapper.fromDto(taskDto));
        return  taskMapper.toDto(createdTask);
    }

    @DeleteMapping(path = "/{task_id}")
    void deleteTask(@PathVariable("task_list_id") UUID taskListId,  @PathVariable("task_id") UUID taskId)
    {
        taskService.deleteTask(taskListId, taskId);
    }

    
}
