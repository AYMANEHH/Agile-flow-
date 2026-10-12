
package com.example.gestion.de.projets.agiles.Controller;

import com.example.gestion.de.projets.agiles.Entity.Task;
import com.example.gestion.de.projets.agiles.Service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/projects/{projectId}/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(
            @PathVariable Long projectId,
            @RequestBody Task task) {
        return taskService.createTask(projectId, task);
    }

    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/projects/{projectId}/tasks")
    public List<Task> getTasksByProject(
            @PathVariable Long projectId) {
        return taskService.getTasksByProject(projectId);
    }

    @GetMapping("/tasks/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @DeleteMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    @PatchMapping("/tasks/{id}/status")
    public Task updateTaskStatus(
            @PathVariable Long id,
            @RequestBody java.util.Map<String, String> body) {

        String status = body.get("status");

        if (status == null) {
            throw new org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.BAD_REQUEST,
                    "Le statut est obligatoire");
        }

        try {
            return taskService.updateTaskStatus(id, status);
        } catch (IllegalArgumentException e) {
            throw new org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.NOT_FOUND,
                    e.getMessage());
        }
    }

    @PutMapping("/tasks/{id}")
    public Task updateTask(
            @PathVariable Long id,
            @RequestBody Task task) {

        try {
            return taskService.updateTask(id, task);
        } catch (IllegalArgumentException e) {
            throw new org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.BAD_REQUEST,
                    e.getMessage());
        }
    }
    @PatchMapping("/tasks/{taskId}/assignee/{userId}")
    public Task assignTask(
            @PathVariable Long taskId,
            @PathVariable Long userId) {
        try {
            return taskService.assignTask(taskId, userId);
        } catch (IllegalArgumentException e) {
            throw new org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.NOT_FOUND,
                    e.getMessage());
        }
    }


}
