package com.example.gestion.de.projets.agiles.Repository;

import com.example.gestion.de.projets.agiles.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByProjectId(Long projectId);
}
