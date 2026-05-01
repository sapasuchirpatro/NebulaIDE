package com.nebulaide.controlplane.modules.projects.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectDTO {

    @Size(max = 40, message = "Project name cannot exceed 40 characters")
    @NotBlank(message = "Project name cannot be empty")
    private String projectName;

    @Size(max = 20, message = "Programming language cannot exceed 20 characters")
    @NotBlank(message = "Programming language cannot be empty")
    private String language;

    @Size(max = 300, message = "Description cannot exceed 300 characters")
    private String description;

}
