package com.example.TaskManagementSystem.service;


import com.example.TaskManagementSystem.contract.TaskRequest;
import com.example.TaskManagementSystem.model.Status;
import com.example.TaskManagementSystem.model.TaskManagement;
import com.example.TaskManagementSystem.repository.TaskManagementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TaskManagementServiceTests {
    private TaskManagementRepository taskManagementRepository;
    private TaskManagementService taskManagementService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        taskManagementRepository = Mockito.mock(TaskManagementRepository.class);
        taskManagementService = new TaskManagementService(taskManagementRepository);
    }
    @Test
    void testCreateTask(){
        TaskManagement entity = new TaskManagement();
        entity.setTaskName("abc");
        entity.setAssigneeId(2223);
        entity.setId(1L);
        entity.setComments("java");
        entity.setStatus(Status.START);
        TaskRequest request = new TaskRequest(entity.getTaskName(),entity.getAssigneeId(),entity.getComments(),entity.getStatus());
        when(taskManagementRepository.save(any())).thenReturn(entity);
        long id = taskManagementService.createTask(request);

        assertEquals(id,1L);
    }

    @Test
    void testGetTaskById(){
        TaskManagement entity = new TaskManagement();
        entity.setTaskName("abc");
        entity.setAssigneeId(2223);
        entity.setId(1L);
        entity.setComments("java");
        entity.setStatus(Status.START);

        long idNo = 1L;
        when(taskManagementRepository.save(any())).thenReturn(entity);
        when(taskManagementRepository.findById(idNo)).thenReturn(entity);
        TaskManagement task = taskManagementService.getTaskById(idNo);

        assertEquals(task.getId(),1L);
    }

    @Test
    void testGetAllTask(){
        TaskManagement entity = new TaskManagement();
        entity.setTaskName("abc");
        entity.setAssigneeId(2223);
        entity.setId(1L);
        entity.setComments("java");
        entity.setStatus(Status.START);
        List<TaskManagement> result = new ArrayList<>();
        result.add(entity);

        when(taskManagementRepository.save(any())).thenReturn(result);
        when(taskManagementRepository.findAll()).thenReturn(result);
        List<TaskManagement> task = taskManagementService.getAllTasks();

        assertEquals(task, result );
    }

    @Test
    void testUpdateTask(){
        TaskManagement entity = new TaskManagement();
        entity.setTaskName("abc");
        entity.setAssigneeId(2223);
        entity.setId(1L);
        entity.setComments("java");
        entity.setStatus(Status.START);
        long idNo = 1L;
        TaskRequest request = new TaskRequest(entity.getTaskName(),entity.getAssigneeId(),entity.getComments(),entity.getStatus());
        when(taskManagementRepository.save(any())).thenReturn(entity);
        when(taskManagementRepository.findById(idNo)).thenReturn(entity);
        long id = taskManagementService.updateTask(request,entity.getId());

        assertEquals(id,1L);
    }

    @Test
    void testDeleteTask(){
        long idNo = 1L;
        when(taskManagementRepository.save(any())).thenReturn(idNo);
        long id = taskManagementService.deleteTask(idNo);

        assertEquals(id,1L);
    }

    @Test
    void testUpdateTaskStatus(){
        TaskManagement entity = new TaskManagement();
        entity.setTaskName("abc");
        entity.setAssigneeId(2223);
        entity.setId(1L);
        entity.setComments("java");
        entity.setStatus(Status.START);
        long idNo = 1L;
        TaskRequest request = new TaskRequest(entity.getTaskName(),entity.getAssigneeId(),entity.getComments(),entity.getStatus());
        when(taskManagementRepository.save(any())).thenReturn(entity);
        when(taskManagementRepository.findById(idNo)).thenReturn(entity);
        long id = taskManagementService.updateStatusOfTask(request,entity.getId());

        assertEquals(id,1L);
    }

    @Test
    void testGetTaskByStatus(){
        TaskManagement entity = new TaskManagement();
        entity.setTaskName("abc");
        entity.setAssigneeId(2223);
        entity.setId(1L);
        entity.setComments("java");
        entity.setStatus(Status.START);
        List<TaskManagement> result = new ArrayList<>();
        result.add(entity);

        when(taskManagementRepository.save(any())).thenReturn(result);
        when(taskManagementRepository.findByStatus(entity.getStatus())).thenReturn(result);
        List<TaskManagement> task = taskManagementService.getTaskByStatus(entity.getStatus());

        assertEquals(task,result);
    }

    @Test
    void testGetTaskByAssignee(){
        TaskManagement entity = new TaskManagement();
        entity.setTaskName("abc");
        entity.setAssigneeId(2223);
        entity.setId(1L);
        entity.setComments("java");
        entity.setStatus(Status.START);
        List<TaskManagement> result = new ArrayList<>();
        result.add(entity);

        when(taskManagementRepository.save(any())).thenReturn(result);
        when(taskManagementRepository.findByAssigneeId(entity.getAssigneeId())).thenReturn(result);
        List<TaskManagement> task = taskManagementService.getTaskByAssignee(entity.getAssigneeId());

        assertEquals(task,result);
    }

    @Test
    void testAddComments(){
        TaskManagement entity = new TaskManagement();
        entity.setTaskName("abc");
        entity.setAssigneeId(2223);
        entity.setId(1L);
        entity.setComments("java");
        entity.setStatus(Status.START);
        long idNo = 1L;
        TaskRequest request = new TaskRequest(entity.getTaskName(),entity.getAssigneeId(),entity.getComments(),entity.getStatus());
        when(taskManagementRepository.save(any())).thenReturn(entity);
        when(taskManagementRepository.findById(idNo)).thenReturn(entity);
        long id = taskManagementService.addCommentToTask(request,entity.getId());

        assertEquals(id,1L);
    }

    @Test
    void testGetCommentsOfTask(){
        TaskManagement entity = new TaskManagement();
        entity.setTaskName("abc");
        entity.setAssigneeId(2223);
        entity.setId(1L);
        entity.setComments("java");
        entity.setStatus(Status.START);

        long idNo = 1L;
        when(taskManagementRepository.save(any())).thenReturn(entity);
        when(taskManagementRepository.findById(idNo)).thenReturn(entity);
        String comment = taskManagementService.getAllCommentsOfTasks(idNo);

        assertEquals(comment,entity.getComments());
    }
}
