package com.example.taskmanagement;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {

    @Query(value = "SELECT * FROM tasks;", nativeQuery = true)
    List<Task> getTasks();

    @Query(value = "SELECT * FROM tasks WHERE id = ?1", nativeQuery = true)
    Task getTaskById(Long id);

}
