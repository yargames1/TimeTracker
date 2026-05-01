package com.intern.timetracker.dto.TimeRecord;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateTimeRecordRequest {

    private Long employeeId;

    private Long taskId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String description;
}