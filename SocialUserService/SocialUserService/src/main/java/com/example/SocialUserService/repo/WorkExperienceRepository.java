package com.example.SocialUserService.repo;

import com.example.SocialUserService.entity.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WorkExperienceRepository extends JpaRepository<WorkExperience, Long> {
    List<WorkExperience> findByUserProfileId(Long userProfileId);
}
