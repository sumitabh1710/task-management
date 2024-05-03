package com.example.taskmanagement;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public ResponseEntity<List<Task>> getAllTasks() {

        List<Task> tasksList = taskRepository.getTasks();

        return ResponseEntity.ok().body(tasksList);
    }

    public ResponseEntity<Object> getTaskById(Long id) {
        Task existingTask = taskRepository.getTaskById(id);

        if (existingTask == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Task does not exist."));
        }

        return ResponseEntity.ok().body(existingTask);
    }

    public ResponseEntity<Object> addTask(Task task) {

        if (task.getTitle() == null || task.getTitle().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Title is required !"));
        }

        Task savedTask = taskRepository.save(task);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);

    }

    public ResponseEntity<Object> deleteTask(Long id) {

        Task existingTask = taskRepository.getTaskById(id);

        if (existingTask == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Task does not exist."));
        }

        taskRepository.delete(existingTask);

        return ResponseEntity.ok().body(Map.of("message", "Task deleted successfully."));

    }

    public ResponseEntity<Object> updateTask(Long id, Task task) {

        Task existingTask = taskRepository.getTaskById(id);

        if (existingTask == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Task does not exist."));
        }

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());

        taskRepository.save(existingTask);

        return ResponseEntity.ok().body(existingTask);

    }

}
