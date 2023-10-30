package com.example.TaskManagementSystem.controller;

import com.example.TaskManagementSystem.contract.TaskRequest;
import com.example.TaskManagementSystem.model.Status;
import com.example.TaskManagementSystem.model.TaskManagement;
import com.example.TaskManagementSystem.service.TaskManagementService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskManagementController {
    private final TaskManagementService taskManagementService;

    public TaskManagementController(TaskManagementService taskManagementService) {
        this.taskManagementService = taskManagementService;
    }

    @PostMapping("/create")
    public ResponseEntity<Long> createTask(@RequestBody TaskRequest request){
        long id = this.taskManagementService.createTask(request);
        return ResponseEntity.ok(id);
    }

    @RequestMapping("/get/{id}")
    public @ResponseBody TaskManagement getTaskById(@PathVariable long id){
        return this.taskManagementService.getTaskById(id);
    }

    @RequestMapping("/get/all")
    public @ResponseBody List<TaskManagement> getTask(){
        return this.taskManagementService.getAllTasks();
    }

    @PutMapping("/{id}")
    public @ResponseBody Long updateTask(@RequestBody TaskRequest request, @PathVariable Long id){
        return this.taskManagementService.updateTask(request,id);
    }

    @DeleteMapping("/{id}")
    public Long deleteTask(@PathVariable long id) throws ChangeSetPersister.NotFoundException {
        return this.taskManagementService.deleteTask(id);
        //return ResponseEntity.ok("successfully deleted");
    }

    @PatchMapping("/{id}/status")
    public @ResponseBody Long updateStatusOfTask(@RequestBody TaskRequest request, @PathVariable Long id){
        return this.taskManagementService.updateStatusOfTask(request,id);
    }

    @GetMapping("/status/{status}")
    public @ResponseBody List<TaskManagement> getTaskByStatus(@PathVariable Status status){
        return this.taskManagementService.getTaskByStatus(status);
    }

    @GetMapping("/assignee/{assigneeId}")
    public @ResponseBody List<TaskManagement> getTaskByAssignee(@PathVariable long assigneeId){
        return this.taskManagementService.getTaskByAssignee(assigneeId);
    }

    @PostMapping("/{id}/comments")
    public @ResponseBody Long addCommentToTask(@RequestBody TaskRequest request, @PathVariable long id){
        return this.taskManagementService.addCommentToTask(request,id);
    }

    @GetMapping("/{id}/comments")
    public @ResponseBody String getCommentsOfTask(@PathVariable long id){
        return this.taskManagementService.getAllCommentsOfTasks(id);
    }
}
