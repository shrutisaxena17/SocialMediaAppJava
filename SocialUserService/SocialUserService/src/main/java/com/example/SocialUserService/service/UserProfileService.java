package com.example.SocialUserService.service;

import com.example.SocialUserService.dto.UserProfileDTO;
import com.example.SocialUserService.entity.UserProfile;
import com.example.SocialUserService.entity.Users;
import com.example.SocialUserService.repo.UserProfileRepository;
import com.example.SocialUserService.repo.UsersRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UsersRepo usersRepo;

    public UserProfileService(UserProfileRepository userProfileRepository, UsersRepo usersRepo) {
        this.userProfileRepository = userProfileRepository;
        this.usersRepo = usersRepo;
    }

    public List<UserProfileDTO> getAllProfiles() {
        return userProfileRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public UserProfileDTO getProfileById(Long id) {
        UserProfile userProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Profile not found with ID: " + id));
        return convertToDTO(userProfile);
    }

    @Transactional
    public UserProfileDTO saveProfile(UserProfileDTO userProfileDTO) {
        Users user = usersRepo.findById(userProfileDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserProfile userProfile = new UserProfile();
        userProfile.setUser(user);
        userProfile.setName(userProfileDTO.getName());
        userProfile.setBio(userProfileDTO.getBio());
        userProfile.setProfilePictureUrl(userProfileDTO.getProfilePictureUrl());
        userProfile.setSkills(userProfileDTO.getSkills());
        userProfile.setFollowersCount(userProfileDTO.getFollowersCount());
        userProfile.setFollowingCount(userProfileDTO.getFollowingCount());

        UserProfile savedProfile = userProfileRepository.save(userProfile);
        return convertToDTO(savedProfile);
    }

    @Transactional
    public void deleteProfile(Long id) {
        if (!userProfileRepository.existsById(id)) {
            throw new RuntimeException("Cannot delete. User Profile not found with ID: " + id);
        }
        userProfileRepository.deleteById(id);
    }

    private UserProfileDTO convertToDTO(UserProfile userProfile) {
        return new UserProfileDTO(
                userProfile.getUser().getId().toString(),
                userProfile.getName(),
                userProfile.getBio(),
                userProfile.getProfilePictureUrl(),
                userProfile.getSkills(),
                userProfile.getFollowersCount(),
                userProfile.getFollowingCount()
        );
    }
}
