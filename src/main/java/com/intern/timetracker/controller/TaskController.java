package com.intern.timetracker.controller;

import com.intern.timetracker.dto.Task.CreateTaskRequest;
import com.intern.timetracker.dto.Task.UpdateTaskStatusRequest;
import com.intern.timetracker.model.Task;
import com.intern.timetracker.model.TaskStatus;
import com.intern.timetracker.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Создание задачи
    @PostMapping
    public Task createTask(@Valid @RequestBody CreateTaskRequest request) {
        return taskService.createTask(
                request.getTitle(),
                request.getDescription()
        );
    }

    // Получение информации о задаче по ID
    @GetMapping("/{id}")
    public Task getTask(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    // Изменение статуса задачи
    @PatchMapping("/{id}/status")
    public void updateStatus(@PathVariable Long id,
                             @Valid @RequestBody UpdateTaskStatusRequest request) {
        taskService.updateTaskStatus(id, request.getStatus());
    }
}
