package com.intern.timetracker.service;

import com.intern.timetracker.exception.NotFoundException;
import com.intern.timetracker.mapper.TaskMapper;
import com.intern.timetracker.model.Task;
import com.intern.timetracker.model.TaskStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private TaskService taskService;

    @Test
    void createTask_shouldSetStatusNew() {
        Task task = taskService.createTask("title", "desc");

        assertEquals(TaskStatus.NEW, task.getStatus());
        verify(taskMapper).insert(any(Task.class));
    }

    @Test
    void getTaskById_shouldReturnTask() {
        Task task = new Task();
        task.setId(1L);

        when(taskMapper.findById(1L)).thenReturn(task);

        Task result = taskService.getTaskById(1L);

        assertEquals(1L, result.getId());
    }

    @Test
    void getTaskById_shouldThrowException() {
        when(taskMapper.findById(1L)).thenReturn(null);

        assertThrows(NotFoundException.class, () -> {
            taskService.getTaskById(1L);
        });
    }

    @Test
    void updateStatus_shouldWork() {
        when(taskMapper.updateStatus(1L, "DONE")).thenReturn(1);

        taskService.updateTaskStatus(1L, TaskStatus.DONE);

        verify(taskMapper).updateStatus(1L, "DONE");
    }

    @Test
    void updateStatus_shouldThrowIfNotFound() {
        when(taskMapper.updateStatus(1L, "DONE")).thenReturn(0);

        assertThrows(NotFoundException.class, () -> {
            taskService.updateTaskStatus(1L, TaskStatus.DONE);
        });
    }
}
