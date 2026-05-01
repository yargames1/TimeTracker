package com.intern.timetracker.dto.TimeRecord;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateTimeRecordRequest {

    @NotNull(message = "EmployeeId must not be null")
    private Long employeeId;

    @NotNull(message = "TaskId must not be null")
    private Long taskId;

    @NotNull(message = "Start time must not be null")
    private LocalDateTime startTime;

    @NotNull(message = "End time must not be null")
    private LocalDateTime endTime;

    @Size(max = 1000, message = "Description too long")
    private String description;
}