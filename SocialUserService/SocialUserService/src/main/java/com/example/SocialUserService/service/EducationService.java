package com.example.SocialUserService.service;

import com.example.SocialUserService.dto.EducationDTO;
import com.example.SocialUserService.entity.Education;
import com.example.SocialUserService.entity.UserProfile;
import com.example.SocialUserService.repo.EducationRepository;
import com.example.SocialUserService.repo.UserProfileRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EducationService {

    private final EducationRepository educationRepository;
    private final UserProfileRepository userProfileRepository;

    public EducationService(EducationRepository educationRepository, UserProfileRepository userProfileRepository) {
        this.educationRepository = educationRepository;
        this.userProfileRepository = userProfileRepository;
    }

    public List<EducationDTO> getEducationByUserProfileId(Long userProfileId) {
        return educationRepository.findByUserProfileId(userProfileId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<EducationDTO> getEducationById(Long id) {
        return educationRepository.findById(id).map(this::convertToDTO);
    }

    @Transactional
    public EducationDTO saveEducation(EducationDTO educationDTO) {
        UserProfile userProfile = userProfileRepository.findById(educationDTO.getUserProfileId())
                .orElseThrow(() -> new RuntimeException("User Profile not found with ID: " + educationDTO.getUserProfileId()));

        Education education = new Education();
        education.setInstitution(educationDTO.getInstitution());
        education.setDegree(educationDTO.getDegree());
        education.setFieldOfStudy(educationDTO.getFieldOfStudy());
        education.setStartDate(educationDTO.getStartDate());
        education.setEndDate(educationDTO.getEndDate());
        education.setUserProfile(userProfile);

        return convertToDTO(educationRepository.save(education));
    }

    @Transactional
    public void deleteEducation(Long id) {
        if (!educationRepository.existsById(id)) {
            throw new RuntimeException("Education not found with ID: " + id);
        }
        educationRepository.deleteById(id);
    }

    private EducationDTO convertToDTO(Education education) {
        return new EducationDTO(
                education.getId(),
                education.getInstitution(),
                education.getDegree(),
                education.getFieldOfStudy(),
                education.getStartDate(),
                education.getEndDate(),
                education.getUserProfile().getId()
        );
    }
}
