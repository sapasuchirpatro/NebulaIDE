package com.nebulaide.controlplane.modules.projects.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class ProjectResponseDTO {
    private UUID id;
    private String projectName;
    private String language;
    private String description;
}
