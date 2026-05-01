package com.nebulaide.controlplane.modules.projects;

import com.nebulaide.controlplane.modules.projects.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {

    boolean existsByProjectName(String projectName);
}
