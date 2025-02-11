package com.example.SocialUserService.controller;

import com.example.SocialUserService.entity.WorkExperience;
import com.example.SocialUserService.service.WorkExperienceService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/work-experiences")
public class WorkExperienceController {

    private final WorkExperienceService workExperienceService;

    public WorkExperienceController(WorkExperienceService workExperienceService) {
        this.workExperienceService = workExperienceService;
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{userProfileId}")
    public ResponseEntity<List<WorkExperience>> getWorkExperiences(@PathVariable Long userProfileId) {
        List<WorkExperience> experiences = workExperienceService.getWorkExperiencesByUserProfileId(userProfileId);
        if (experiences.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(experiences);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<WorkExperience> addWorkExperience(@RequestBody WorkExperience workExperience) {
        WorkExperience savedExperience = workExperienceService.saveWorkExperience(workExperience);
        return ResponseEntity.status(201).body(savedExperience);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkExperience(@PathVariable Long id) {
        Optional<WorkExperience> experience = workExperienceService.getWorkExperienceById(id);
        if (experience.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        workExperienceService.deleteWorkExperience(id);
        return ResponseEntity.noContent().build();
    }
}
