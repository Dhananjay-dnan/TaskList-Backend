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

import com.Project.ToDoList.domain.dto.TaskListDto;
import com.Project.ToDoList.domain.entities.TaskList;
import com.Project.ToDoList.mappers.TaskListMapper;
import com.Project.ToDoList.services.TaskListService;

@RestController
@RequestMapping(path = "/task-lists")
public class TaskListController {

    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }

    @GetMapping
    public List<TaskListDto> listTaskLists()
    {
        return taskListService.listTaskLists().stream().map(taskListMapper::toDto).toList();
    }
    
    @GetMapping(path = "/{task_list_id}")
    public Optional<TaskListDto> getTaskList(@PathVariable("task_list_id") UUID taskListId)
    {
        return taskListService.getTaskList(taskListId).map(taskListMapper::toDto);
    }

    @PutMapping(path = "/{task_list_id}")
    public TaskListDto updateTaskList(@PathVariable("task_list_id") UUID taskListId, @RequestBody TaskListDto taskListDto){
        TaskList updatedTaskList = taskListService.updateTaskList(taskListId, taskListMapper.fromDto(taskListDto));

        return taskListMapper.toDto(updatedTaskList);
    }

    @PostMapping
    public  TaskListDto createTaskList(@RequestBody TaskListDto taskListDto)
    {
        TaskList createdTaskList = taskListService.createTaskList(taskListMapper.fromDto(taskListDto));

        return taskListMapper.toDto(createdTaskList);
    }

    @DeleteMapping(path = "/{task_list_id}")
    public void deleteTaskList(@PathVariable("task_list_id") UUID taskListId)
    {
        taskListService.deleteTaskList(taskListId);
    }
}
