package com.intern.timetracker.service;

import com.intern.timetracker.exception.NotFoundException;
import com.intern.timetracker.mapper.TaskMapper;
import com.intern.timetracker.model.Task;
import com.intern.timetracker.model.TaskStatus;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskMapper taskMapper;

    public TaskService(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    public Task createTask(String title, String description) {
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setStatus(TaskStatus.NEW);

        taskMapper.insert(task);

        return task;
    }

    public Task getTaskById(Long id) {
        Task task = taskMapper.findById(id);

        if (task == null) {
            throw new NotFoundException("Task not found with id: " + id);
        }

        return task;
    }

    public void updateTaskStatus(Long id, TaskStatus status) {
        int updatedRows = taskMapper.updateStatus(id, status.name());

        if (updatedRows == 0) {
            throw new NotFoundException("Task not found with id: " + id);
        }
    }
}
