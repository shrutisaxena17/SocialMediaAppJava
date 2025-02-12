package com.example.SocialUserService.controller;

import com.example.SocialUserService.dto.UserProfileDTO;
import com.example.SocialUserService.service.UserProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-profiles")
public class UserProfileController {

    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserProfileDTO>> getAllProfiles() {
        List<UserProfileDTO> profiles = userProfileService.getAllProfiles();
        return ResponseEntity.ok(profiles);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserProfileDTO> getProfileById(@PathVariable Long id) {
        UserProfileDTO profile = userProfileService.getProfileById(id);
        return ResponseEntity.ok(profile);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<UserProfileDTO> createProfile(@RequestBody UserProfileDTO userProfileDTO) {
        UserProfileDTO savedProfile = userProfileService.saveProfile(userProfileDTO);
        return ResponseEntity.status(201).body(savedProfile);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        userProfileService.deleteProfile(id);
        return ResponseEntity.noContent().build();
    }
}
