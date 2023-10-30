package com.example.TaskManagementSystem.model;

public enum Status {
    START,
    ASSIGNED,
    INPROGRESS,
    COMPLETED;
    public static Status DEFAULT = START;
}
