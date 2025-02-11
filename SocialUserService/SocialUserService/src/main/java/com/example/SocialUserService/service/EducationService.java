package com.example.SocialUserService.service;

import com.example.SocialUserService.entity.Education;
import com.example.SocialUserService.repo.EducationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EducationService {

    private final EducationRepository educationRepository;

    public EducationService(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    public List<Education> getEducationByUserProfileId(Long userProfileId) {
        return educationRepository.findByUserProfileId(userProfileId);
    }

    public Optional<Education> getEducationById(Long id) {
        return educationRepository.findById(id);
    }

    @Transactional
    public Education saveEducation(Education education) {
        return educationRepository.save(education);
    }

    @Transactional
    public void deleteEducation(Long id) {
        if (!educationRepository.existsById(id)) {
            throw new RuntimeException("Education not found with ID: " + id);
        }
        educationRepository.deleteById(id);
    }
}
