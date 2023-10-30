package com.example.TaskManagementSystem.contract;

import com.example.TaskManagementSystem.model.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskRequest {
    private String taskName;

    private long assigneeId;

    private String comments;

    @Enumerated(EnumType.STRING)
    private Status status;

    public TaskRequest(String taskName, long assigneeId, String comments, Status status) {
        this.taskName = taskName;
        this.assigneeId = assigneeId;
        this.comments = comments;
        this.status = status;
    }
}
