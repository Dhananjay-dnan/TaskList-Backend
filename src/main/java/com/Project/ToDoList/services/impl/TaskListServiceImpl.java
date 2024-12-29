package com.Project.ToDoList.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.Project.ToDoList.domain.entities.TaskList;
import com.Project.ToDoList.repositories.TaskListRepository;
import com.Project.ToDoList.services.TaskListService;

import jakarta.transaction.Transactional;

@Service
public class TaskListServiceImpl implements TaskListService{

    private final TaskListRepository taskListRepository;

    public TaskListServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override   
    public List<TaskList> listTaskLists(){
        return taskListRepository.findAll();
    }

    @Override
    public TaskList createTaskList(TaskList taskList){
        if(taskList.getId()!=null)
            throw new IllegalArgumentException("Already have an Id");
        if(taskList.getTitle()==null && taskList.getTitle().isEmpty())
            throw new IllegalArgumentException("Title must not be empty");
        LocalDateTime now = LocalDateTime.now();
        return taskListRepository.save(new TaskList(
            null,
            taskList.getTitle(),
            taskList.getDescription(),
            null,
            now,now)
        );
    }

    @Override
    public Optional<TaskList> getTaskList(UUID id)
    {
        return taskListRepository.findById(id);
    }

    @Transactional
    @Override
    public TaskList updateTaskList(UUID taskListId, TaskList taskList)
    {
        if(taskList.getId() == null)
            throw new  IllegalArgumentException("ID is required");
       if(!Objects.equals(taskListId, taskList.getId()))
            throw new IllegalArgumentException("Attempting to Change Id. !!!Not Allowed");
        TaskList existingTaskList = taskListRepository.findById(taskListId).orElseThrow(()-> new IllegalArgumentException("No TaskList Found"));
        existingTaskList.setTitle(taskList.getTitle());
        existingTaskList.setDescription(taskList.getDescription());
        existingTaskList.setUpdated(LocalDateTime.now());
         
        return taskListRepository.save(existingTaskList);
    }

    @Override
    public void deleteTaskList(UUID taskListId){
        taskListRepository.deleteById(taskListId);
    }
}
