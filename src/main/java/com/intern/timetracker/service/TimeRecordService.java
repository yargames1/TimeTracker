package com.intern.timetracker.service;

import com.intern.timetracker.exception.NotFoundException;
import com.intern.timetracker.mapper.TaskMapper;
import com.intern.timetracker.mapper.TimeRecordMapper;
import com.intern.timetracker.model.Task;
import com.intern.timetracker.model.TimeRecord;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TimeRecordService {

    private final TimeRecordMapper timeRecordMapper;
    private final TaskMapper taskMapper;

    public TimeRecordService(TimeRecordMapper timeRecordMapper,
                             TaskMapper taskMapper) {
        this.timeRecordMapper = timeRecordMapper;
        this.taskMapper = taskMapper;
    }

    public TimeRecord createTimeRecord(Long employeeId,
                                       Long taskId,
                                       LocalDateTime startTime,
                                       LocalDateTime endTime,
                                       String description) {

        // Проверяем, что задача существует
        Task task = taskMapper.findById(taskId);
        if (task == null) {
            throw new NotFoundException("Task not found with id: " + taskId);
        }

        // Проверка времени
        if (startTime == null || endTime == null) {
            throw new RuntimeException("Start time and end time must not be null");
        }

        if (endTime.isBefore(startTime)) {
            throw new RuntimeException("End time must be after start time");
        }

        // Создаём запись
        TimeRecord record = new TimeRecord();
        record.setEmployeeId(employeeId);
        record.setTaskId(taskId);
        record.setStartTime(startTime);
        record.setEndTime(endTime);
        record.setDescription(description);

        timeRecordMapper.insert(record);

        return record;
    }

    public List<TimeRecord> getTimeRecords(Long employeeId,
                                           LocalDateTime start,
                                           LocalDateTime end) {

        if (start == null || end == null) {
            throw new RuntimeException("Start and end must not be null");
        }

        if (end.isBefore(start)) {
            throw new RuntimeException("End must be after start");
        }

        return timeRecordMapper.findByEmployeeIdAndPeriod(employeeId, start, end);
    }
}