package com.intern.timetracker.dto.Task;

import com.intern.timetracker.model.TaskStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTaskStatusRequest {

    @NotNull(message = "Status must not be null")
    private TaskStatus status;
}
