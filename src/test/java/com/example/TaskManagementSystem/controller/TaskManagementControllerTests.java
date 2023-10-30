package com.example.TaskManagementSystem.controller;

import com.example.TaskManagementSystem.contract.TaskRequest;
import com.example.TaskManagementSystem.model.Status;
import com.example.TaskManagementSystem.model.TaskManagement;
import com.example.TaskManagementSystem.service.TaskManagementService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskManagementControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskManagementService taskManagementService;

    @Test
    void testCreateTask() throws  Exception {
        String path = "/tasks/create";
        TaskManagement entity = new TaskManagement();
        entity.setTaskName("abc");
        entity.setAssigneeId(2223);
        entity.setId(1L);
        entity.setComments("java");
        entity.setStatus(Status.START);
        TaskRequest taskRequest = new TaskRequest(entity.getTaskName(),entity.getAssigneeId(),entity.getComments(),entity.getStatus());

        //Given
        when(taskManagementService.createTask(taskRequest)).thenReturn(1L);

        ObjectMapper objectMapper = new ObjectMapper();
        String request = objectMapper.writeValueAsString(taskRequest);

        //Then
        mockMvc.perform(post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print())

                //when
                .andExpect(status().isOk());
    }

    @Test
    void testGetTaskById() throws Exception{
        TaskManagement entity = new TaskManagement();
        entity.setTaskName("abc");
        entity.setAssigneeId(2223);
        entity.setId(1L);
        entity.setComments("java");
        entity.setStatus(Status.START);
        long id = 1L;

        //Given
        when(taskManagementService.getTaskById(id)).thenReturn(entity);
        when(taskManagementService.getTaskById(entity.getId())).thenReturn(entity);

        mockMvc.perform(get("/tasks/get/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllTask() throws Exception{
        TaskManagement entity = new TaskManagement();
        entity.setTaskName("abc");
        entity.setAssigneeId(2223);
        entity.setId(1L);
        entity.setComments("java");
        entity.setStatus(Status.START);
        List<TaskManagement> result = new ArrayList<>();
        result.add(entity);

        //Given
        when(taskManagementService.getAllTasks()).thenReturn((result));

        mockMvc.perform(get("/tasks/get/all"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateTask() throws Exception{
        String path = "/tasks/1";
        TaskManagement entity = new TaskManagement();
        entity.setTaskName("abc");
        entity.setAssigneeId(2223);
        entity.setId(1L);
        entity.setComments("java");
        entity.setStatus(Status.START);

        TaskRequest taskRequest = new TaskRequest(entity.getTaskName(),entity.getAssigneeId(),entity.getComments(),entity.getStatus());

        //Given
        when(taskManagementService.updateTask(taskRequest, entity.getId())).thenReturn(1L);

        ObjectMapper objectMapper = new ObjectMapper();
        String request = objectMapper.writeValueAsString(taskRequest);

        mockMvc.perform(put(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))

                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testDelete() throws Exception{
        String uri = "/1";
        long id = 1L;
        //Given
        when(taskManagementService.deleteTask(1L)).thenReturn((1L));

        mockMvc.perform(delete("/tasks/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateTaskStatus() throws Exception{
        String path = "/tasks/1/status";
        TaskManagement entity = new TaskManagement();
        entity.setTaskName("abc");
        entity.setAssigneeId(2223);
        entity.setId(1L);
        entity.setComments("java");
        entity.setStatus(Status.START);

        long id = 1L;
        TaskRequest taskRequest = new TaskRequest(entity.getTaskName(),entity.getAssigneeId(),entity.getComments(),entity.getStatus());

        when(taskManagementService.updateStatusOfTask(taskRequest, 1L)).thenReturn((id));
        ObjectMapper objectMapper = new ObjectMapper();
        String request = objectMapper.writeValueAsString(taskRequest);

        mockMvc.perform(patch(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))

                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    void testGetTaskByStatus() throws Exception{
        String path = "/tasks//status/START";
        TaskManagement entity = new TaskManagement();
        entity.setTaskName("abc");
        entity.setAssigneeId(2223);
        entity.setId(1L);
        entity.setComments("java");
        entity.setStatus(Status.START);
        List<TaskManagement> result = new ArrayList<>();
        result.add(entity);

        when(taskManagementService.getTaskByStatus(entity.getStatus())).thenReturn(result);
        //Then
        mockMvc.perform(get(path))
                .andDo(print())

                //when
                .andExpect(status().isOk());
    }

    @Test
    void testGetTaskByAssignee() throws Exception{
        String path = "/tasks/assignee/2223";
        TaskManagement entity = new TaskManagement();
        entity.setTaskName("abc");
        entity.setAssigneeId(2223);
        entity.setId(1L);
        entity.setComments("java");
        entity.setStatus(Status.START);
        List<TaskManagement> result = new ArrayList<>();
        result.add(entity);

        when(taskManagementService.getTaskByAssignee(entity.getAssigneeId())).thenReturn(result);
        //Then
        mockMvc.perform(get(path))
                .andDo(print())

                //when
                .andExpect(status().isOk());
    }

    @Test
    void testAddComments() throws Exception{
        String path = "/tasks/1/comments";
        TaskManagement entity = new TaskManagement();
        entity.setTaskName("abc");
        entity.setAssigneeId(2223);
        entity.setId(1L);
        entity.setComments("java");
        entity.setStatus(Status.START);

        TaskRequest taskRequest = new TaskRequest(entity.getTaskName(),entity.getAssigneeId(),entity.getComments(),entity.getStatus());
        when(taskManagementService.addCommentToTask(taskRequest, 1L)).thenReturn(entity.getId());
        ObjectMapper objectMapper = new ObjectMapper();
        String request = objectMapper.writeValueAsString(taskRequest);

        mockMvc.perform(post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))

                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllCommentsOfTask() throws Exception{
        String path = "/tasks/1/comments";
        TaskManagement entity = new TaskManagement();
        entity.setTaskName("abc");
        entity.setAssigneeId(2223);
        entity.setId(1L);
        entity.setComments("java");
        entity.setStatus(Status.START);

        //Given
        when(taskManagementService.getAllCommentsOfTasks(entity.getId())).thenReturn(entity.getComments());
        //Then
        mockMvc.perform(get(path))
                .andDo(print())

                //when
                .andExpect(status().isOk());
    }
}
