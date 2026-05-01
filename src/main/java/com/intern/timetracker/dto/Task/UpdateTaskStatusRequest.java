package com.intern.timetracker.dto.Task;

import com.intern.timetracker.model.TaskStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTaskStatusRequest {

    private TaskStatus status;
}
