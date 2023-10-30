package com.example.TaskManagementSystem.repository;

import com.example.TaskManagementSystem.model.Status;
import com.example.TaskManagementSystem.model.TaskManagement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TaskManagementRepository extends CrudRepository<TaskManagement, Long> {
    TaskManagement findById(long id);
    TaskManagement deleteById(long id);

    List<TaskManagement> findByStatus(Status status);

    List<TaskManagement> findByAssigneeId(long assigneeId);

}
