package com.intern.timetracker.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Task {

    private Long id;

    private String title;

    private String description;

    private TaskStatus status;
}