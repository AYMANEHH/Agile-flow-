package com.example.gestion.de.projets.agiles.Repository;

import com.example.gestion.de.projets.agiles.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
