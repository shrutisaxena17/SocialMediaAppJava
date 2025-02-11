package com.example.SocialUserService.service;

import com.example.SocialUserService.entity.WorkExperience;
import com.example.SocialUserService.repo.WorkExperienceRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkExperienceService {

    private final WorkExperienceRepository workExperienceRepository;

    public WorkExperienceService(WorkExperienceRepository workExperienceRepository) {
        this.workExperienceRepository = workExperienceRepository;
    }

    public List<WorkExperience> getWorkExperiencesByUserProfileId(Long userProfileId) {
        return workExperienceRepository.findByUserProfileId(userProfileId);
    }

    public Optional<WorkExperience> getWorkExperienceById(Long id) {
        return workExperienceRepository.findById(id);
    }

    @Transactional
    public WorkExperience saveWorkExperience(WorkExperience workExperience) {
        return workExperienceRepository.save(workExperience);
    }

    @Transactional
    public void deleteWorkExperience(Long id) {
        if (!workExperienceRepository.existsById(id)) {
            throw new RuntimeException("Work Experience not found with ID: " + id);
        }
        workExperienceRepository.deleteById(id);
    }
}
