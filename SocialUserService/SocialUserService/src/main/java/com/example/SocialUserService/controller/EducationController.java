package com.example.SocialUserService.controller;

import com.example.SocialUserService.entity.Education;
import com.example.SocialUserService.service.EducationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/education")
public class EducationController {

    private final EducationService educationService;

    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{userProfileId}")
    public ResponseEntity<List<Education>> getEducation(@PathVariable Long userProfileId) {
        List<Education> educationList = educationService.getEducationByUserProfileId(userProfileId);
        if (educationList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(educationList);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<Education> addEducation(@RequestBody Education education) {
        Education savedEducation = educationService.saveEducation(education);
        return ResponseEntity.status(201).body(savedEducation);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEducation(@PathVariable Long id) {
        Optional<Education> education = educationService.getEducationById(id);
        if (education.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        educationService.deleteEducation(id);
        return ResponseEntity.noContent().build();
    }
}
