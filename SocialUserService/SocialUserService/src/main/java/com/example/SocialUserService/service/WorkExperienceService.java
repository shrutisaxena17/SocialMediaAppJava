package com.example.SocialUserService.service;

import com.example.SocialUserService.dto.WorkExperienceDTO;
import com.example.SocialUserService.entity.UserProfile;
import com.example.SocialUserService.entity.WorkExperience;
import com.example.SocialUserService.repo.UserProfileRepository;
import com.example.SocialUserService.repo.WorkExperienceRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkExperienceService {

    private final WorkExperienceRepository workExperienceRepository;
    private final UserProfileRepository userProfileRepository;

    public WorkExperienceService(WorkExperienceRepository workExperienceRepository, UserProfileRepository userProfileRepository) {
        this.workExperienceRepository = workExperienceRepository;
        this.userProfileRepository = userProfileRepository;
    }

    public List<WorkExperienceDTO> getWorkExperiencesByUserProfileId(Long userProfileId) {
        return workExperienceRepository.findByUserProfileId(userProfileId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<WorkExperienceDTO> getWorkExperienceById(Long id) {
        return workExperienceRepository.findById(id).map(this::convertToDTO);
    }

    @Transactional
    public WorkExperienceDTO saveWorkExperience(WorkExperienceDTO workExperienceDTO) {
        Optional<UserProfile> userProfileOpt = userProfileRepository.findById(workExperienceDTO.getUserProfileId());
        if (userProfileOpt.isEmpty()) {
            throw new RuntimeException("UserProfile not found with ID: " + workExperienceDTO.getUserProfileId());
        }

        WorkExperience workExperience = convertToEntity(workExperienceDTO);
        workExperience.setUserProfile(userProfileOpt.get());

        WorkExperience savedExperience = workExperienceRepository.save(workExperience);
        return convertToDTO(savedExperience);
    }

    @Transactional
    public void deleteWorkExperience(Long id) {
        if (!workExperienceRepository.existsById(id)) {
            throw new RuntimeException("Work Experience not found with ID: " + id);
        }
        workExperienceRepository.deleteById(id);
    }

    private WorkExperienceDTO convertToDTO(WorkExperience workExperience) {
        return new WorkExperienceDTO(
                workExperience.getId(),
                workExperience.getCompany(),
                workExperience.getPosition(),
                workExperience.getStartDate(),
                workExperience.getEndDate(),
                workExperience.isCurrentJob(),
                workExperience.getDescription(),
                workExperience.getUserProfile().getId()
        );
    }

    private WorkExperience convertToEntity(WorkExperienceDTO workExperienceDTO) {
        UserProfile userProfile = userProfileRepository.findById(workExperienceDTO.getUserProfileId())
                .orElseThrow(() -> new RuntimeException("User Profile not found with ID: " + workExperienceDTO.getUserProfileId()));

        return new WorkExperience(
                workExperienceDTO.getId(),
                workExperienceDTO.getCompany(),
                workExperienceDTO.getPosition(),
                workExperienceDTO.getStartDate(),
                workExperienceDTO.getEndDate(),
                workExperienceDTO.isCurrentJob(),
                workExperienceDTO.getDescription(),
                userProfile
        );
    }
}
