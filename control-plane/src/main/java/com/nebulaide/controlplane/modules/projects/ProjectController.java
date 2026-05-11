package com.nebulaide.controlplane.modules.projects;

import com.nebulaide.controlplane.modules.projects.dto.ProjectDTO;
import com.nebulaide.controlplane.modules.projects.dto.ProjectResponseDTO;
import com.nebulaide.sharedcontracts.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/control")
@RequiredArgsConstructor
@Slf4j
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/projects")
    public ResponseEntity<ApiResponse<ProjectResponseDTO>> createProject(@Valid @RequestBody ProjectDTO projectDTO) {
        ProjectResponseDTO createdProject = projectService.createProject(projectDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponse.<ProjectResponseDTO>builder()
                                .data(createdProject)
                                .message("Project Created Successfully")
                                .build()
                );
    }

//    @GetMapping("/projects")
//    public ResponseEntity<?> fetchProjects() {
//        return ResponseEntity.status(HttpStatus.OK)
//                .body("Project fetched");
//    }
}
