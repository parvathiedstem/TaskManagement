package com.example.TaskManagementSystem.service;

import com.example.TaskManagementSystem.contract.TaskRequest;
import com.example.TaskManagementSystem.model.Status;
import com.example.TaskManagementSystem.model.TaskManagement;
import com.example.TaskManagementSystem.repository.TaskManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskManagementService {
    private final TaskManagementRepository taskManagementRepository;

    @Autowired
    public TaskManagementService(TaskManagementRepository taskManagementRepository){

        this.taskManagementRepository = taskManagementRepository;
    }
    public Long createTask(TaskRequest request) {
        TaskManagement task = new TaskManagement(
                request.getTaskName(),
                request.getAssigneeId(),
                request.getComments(),
                Status.DEFAULT
                );
        TaskManagement response = taskManagementRepository.save(task);
        return response.getId();
    }

    public TaskManagement getTaskById(long id) {
        TaskManagement task = this.taskManagementRepository.findById(id);
        if(task == null){
            throw new RuntimeException("task not found");
        }
        return task;
    }

    public List<TaskManagement> getAllTasks() {

        return (List<TaskManagement>)this.taskManagementRepository.findAll();
    }

    public Long updateTask(TaskRequest request, long id) {
        TaskManagement task = this.taskManagementRepository.findById(id);
        if(task == null){
            throw new RuntimeException("task not found");
        }
        task.setTaskName(request.getTaskName());
        task.setAssigneeId(request.getAssigneeId());
        task.setComments(request.getComments());
        task.setStatus(request.getStatus());

        TaskManagement response = taskManagementRepository.save(task);
        return response.getId();
    }

    public long deleteTask(long id) {
        this.taskManagementRepository.deleteById(id);
        return id;
    }

    public Long updateStatusOfTask(TaskRequest request, long id) {
        TaskManagement task = this.taskManagementRepository.findById(id);
        if(task == null){
            throw new RuntimeException("task not found");
        }
        task.setStatus(request.getStatus());
        TaskManagement response = taskManagementRepository.save(task);
        return response.getId();
    }

    public List<TaskManagement> getTaskByStatus(Status status) {
        return this.taskManagementRepository.findByStatus(status);
    }

    public List<TaskManagement> getTaskByAssignee(long assigneeId) {
        return this.taskManagementRepository.findByAssigneeId(assigneeId);
    }

    public Long addCommentToTask(TaskRequest request, long id) {
        TaskManagement task = this.taskManagementRepository.findById(id);
        if(task == null){
            throw new RuntimeException("task not found");
        }
        task.setComments(request.getComments());
        TaskManagement response = taskManagementRepository.save(task);
        return response.getId();
    }

    public String getAllCommentsOfTasks(long id) {
        TaskManagement task = this.taskManagementRepository.findById(id);
        if(task == null){
            throw new RuntimeException("task not found");
        }
        String comments = task.getComments();
        return comments;
    }
}
