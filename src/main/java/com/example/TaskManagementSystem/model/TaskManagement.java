package com.example.TaskManagementSystem.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Setter
@Getter
public class TaskManagement {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private  Long id;

    private String taskName;

    private long assigneeId;

    private String comments;

    @Enumerated(EnumType.STRING)
    private Status status;

    public TaskManagement(String taskName, long assigneeId, String comments, Status status) {
        this.taskName = taskName;
        this.assigneeId = assigneeId;
        this.comments = comments;
        this.status = status;
    }
    public TaskManagement() {
    }

}
