package com.Project.ToDoList.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.Project.ToDoList.domain.entities.Task;
import com.Project.ToDoList.domain.entities.TaskList;
import com.Project.ToDoList.domain.entities.TaskPriority;
import com.Project.ToDoList.domain.entities.TaskStatus;
import com.Project.ToDoList.repositories.TaskListRepository;
import com.Project.ToDoList.repositories.TaskRepository;
import com.Project.ToDoList.services.TaskService;

import jakarta.transaction.Transactional;

@Service
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;
    
    public TaskServiceImpl(TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<Task> listTasks(UUID taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }

    @Override
    public Optional<Task> getTask(UUID taskListId, UUID taskId) {
        return taskRepository.findByTaskListIdAndId(taskListId, taskId);
    }
    
    @Override
    public Task createTask (UUID taskListId, Task task){
        if(task.getId()!=null)
                throw new IllegalArgumentException("Task already has an Id");
        if(null==task.getTitle()||task.getTitle().isBlank())
            throw new IllegalArgumentException("Title should not be blank");
        TaskStatus taskStatus = TaskStatus.OPEN;
        TaskPriority taskPriority = Optional.ofNullable(task.getPriority()).orElse(TaskPriority.MEDIUM);
        TaskList taskList = taskListRepository.findById(taskListId).orElseThrow(() -> new IllegalArgumentException("TaskList not found"));
        LocalDateTime now = LocalDateTime.now();
        Task taskToSave = new Task(null, task.getTitle(), task.getDescription(), task.getDueDate(), taskStatus, taskList, taskPriority, now, now);
        return taskRepository.save(taskToSave);
    }

    @Override
    public Task updateTask(UUID taskListId, UUID taskId, Task task) {
        if(task.getId()==null)
            throw new IllegalArgumentException("Task should have an id");
        if(!Objects.equals(taskId, task.getId()))
            throw new IllegalArgumentException("Id's should be matched");
        if(null==task.getPriority())
            throw new IllegalArgumentException("Priority should be valid");
        if(null==task.getStatus())
            throw new IllegalArgumentException("Status should not be null");
        Task existingTask = taskRepository.findByTaskListIdAndId(taskListId, taskId).orElseThrow(() -> new IllegalArgumentException("Task Not Found"));
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setUpdated(LocalDateTime.now());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setPriority(task.getPriority());
        existingTask.setStatus(TaskStatus.CLOSED);
        return taskRepository.save(existingTask);
    }

    @Transactional
    @Override
    public void deleteTask(UUID taskListId, UUID taskId) {
        taskRepository.deleteByTaskListIdAndId(taskListId, taskId);
    }

}
