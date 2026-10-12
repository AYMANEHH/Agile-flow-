
package com.example.gestion.de.projets.agiles.Service;
import com.example.gestion.de.projets.agiles.Entity.User;
import com.example.gestion.de.projets.agiles.Repository.UserRepository;
import com.example.gestion.de.projets.agiles.Entity.Project;
import com.example.gestion.de.projets.agiles.Entity.Task;
import com.example.gestion.de.projets.agiles.Repository.ProjectRepository;
import com.example.gestion.de.projets.agiles.Repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public TaskService(
            TaskRepository taskRepository,
            ProjectRepository projectRepository,
            UserRepository userRepository){
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public Task createTask(Long projectId, Task task) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Projet introuvable"));

        task.setProject(project);
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getTasksByProject(Long projectId) {
        if (!projectRepository.existsById(projectId)) {
            throw new IllegalArgumentException("Projet introuvable");
        }

        return taskRepository.findByProjectId(projectId);
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Tâche introuvable"));
    }

    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new IllegalArgumentException("Tâche introuvable");
        }

        taskRepository.deleteById(id);
    }

    public Task updateTaskStatus(Long id, String status) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Tâche introuvable"));

        if (!status.equals("TODO")
                && !status.equals("IN_PROGRESS")
                && !status.equals("DONE")) {
            throw new IllegalArgumentException("Statut invalide");
        }

        task.setStatus(status);
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task updatedTask) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Tâche introuvable"));

        if (updatedTask.getTitle() == null
                || updatedTask.getTitle().isBlank()) {
            throw new IllegalArgumentException("Le titre est obligatoire");
        }

        if (updatedTask.getStatus() == null
                || !java.util.Set.of("TODO", "IN_PROGRESS", "DONE")
                .contains(updatedTask.getStatus())) {
            throw new IllegalArgumentException("Statut invalide");
        }

        if (updatedTask.getPriority() == null
                || !java.util.Set.of("LOW", "MEDIUM", "HIGH")
                .contains(updatedTask.getPriority())) {
            throw new IllegalArgumentException("Priorité invalide");
        }

        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setStatus(updatedTask.getStatus());
        task.setPriority(updatedTask.getPriority());

        return taskRepository.save(task);
    }

    public Task assignTask(Long taskId, Long userId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Tâche introuvable"));

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Utilisateur introuvable"));

        task.setAssignee(user);

        return taskRepository.save(task);
    }



}
