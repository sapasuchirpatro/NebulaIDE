package com.nebulaide.controlplane.modules.projects;

import com.nebulaide.controlplane.exception.DuplicateResourceException;
import com.nebulaide.controlplane.modules.projects.dto.ProjectDTO;
import com.nebulaide.controlplane.modules.projects.dto.ProjectResponseDTO;
import com.nebulaide.controlplane.modules.projects.entity.Project;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectService {
    private final ProjectRepository projectRepository;

    @Transactional
    public ProjectResponseDTO createProject(ProjectDTO projectDTO) {
        // TODO: Duplicate check should be specific to user (Should do it in Phase 2)
        boolean isProjectNameExist = checkProjectExist(projectDTO);

        if (!isProjectNameExist) {
            // TODO: Use MapStruct
            Project project = new Project();
            project.setProjectName(projectDTO.getProjectName());
            project.setLanguage(projectDTO.getLanguage());
            project.setDescription(projectDTO.getDescription());
            Project savedProject = projectRepository.save(project);

            // TODO: Use MapStruct
            return ProjectResponseDTO.builder()
                    .id(savedProject.getId())
                    .projectName(savedProject.getProjectName())
                    .language(savedProject.getLanguage())
                    .description(savedProject.getDescription())
                    .build();
        } else {
            throw new DuplicateResourceException("A project with this name already exists.");
        }
    }

    private boolean checkProjectExist(ProjectDTO projectDTO) {
        return projectRepository.existsByProjectName(projectDTO.getProjectName());
    }
}
