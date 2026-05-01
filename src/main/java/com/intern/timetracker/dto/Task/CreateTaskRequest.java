package com.intern.timetracker.dto.Task;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Getter
@Setter
public class CreateTaskRequest {

    @NotBlank(message = "Title must not be empty")
    @Size(max = 255, message = "Title must be at most 100 characters")
    private String title;

    @Size(max = 1000, message = "Description must be at most 500 characters")
    private String description;
}