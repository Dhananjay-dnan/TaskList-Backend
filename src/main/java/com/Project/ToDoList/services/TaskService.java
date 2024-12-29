package com.Project.ToDoList.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.Project.ToDoList.domain.entities.Task;

public interface TaskService {
    List<Task> listTasks(UUID id);
    Optional<Task> getTask(UUID tasListId, UUID taskId);
    Task createTask(UUID taskListId, Task task);
    Task updateTask(UUID taskListId, UUID taskid, Task task);
    void deleteTask(UUID taskListId, UUID taskId);
}
