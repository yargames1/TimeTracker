package com.intern.timetracker.controller;

import com.intern.timetracker.dto.TimeRecord.CreateTimeRecordRequest;
import com.intern.timetracker.model.TimeRecord;
import com.intern.timetracker.service.TimeRecordService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/time-records")
public class TimeRecordController {

    private final TimeRecordService timeRecordService;

    public TimeRecordController(TimeRecordService timeRecordService) {
        this.timeRecordService = timeRecordService;
    }

    // Создать запись о затраченном времени сотрудника на задачу
    @PostMapping
    public TimeRecord createTimeRecord(@RequestBody CreateTimeRecordRequest request) {
        return timeRecordService.createTimeRecord(
                request.getEmployeeId(),
                request.getTaskId(),
                request.getStartTime(),
                request.getEndTime(),
                request.getDescription()
        );
    }

    // Получить информацию о затраченном времени сотрудника на задачи за определённый
    //период времени
    @GetMapping
    public List<TimeRecord> getTimeRecords(
            @RequestParam Long employeeId,
            @RequestParam String start,
            @RequestParam String end
    ) {
        return timeRecordService.getTimeRecords(
                employeeId,
                LocalDateTime.parse(start),
                LocalDateTime.parse(end)
        );
    }
}
