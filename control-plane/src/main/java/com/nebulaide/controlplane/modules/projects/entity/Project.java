package com.nebulaide.controlplane.modules.projects.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.UUID;

@Entity
@Data
//@Table(name = "projects")
//@Table(
//    name = "project",
//    uniqueConstraints = {
//        @UniqueConstraint(
//            name = "uk_project_project_name",
//            columnNames = "project_name"
//        )
//    }
//)
@EntityListeners(AuditingEntityListener.class)
public class Project {

    // TODO: We can use time base UUID to eleminate the chance of duplicacy and it help in indexing as well.
    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank // it will take care NULL, Empty and Blank (String with spaces)
    @Size(max = 40)
    @Column(unique = true, nullable = false, length = 40) // This will validate the constraint in the database
    private String projectName;

    @Size(max = 20)
    @Column(length = 20)
    private String language;

    @Size(max = 300)
    @Column(length = 300)
    private String description;

    @CreatedDate
    @Column(nullable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Instant updatedAt;

}
