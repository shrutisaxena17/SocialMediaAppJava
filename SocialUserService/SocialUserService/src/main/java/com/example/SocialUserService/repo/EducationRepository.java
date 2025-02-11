package com.example.SocialUserService.repo;

import com.example.SocialUserService.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EducationRepository extends JpaRepository<Education, Long> {
    List<Education> findByUserProfileId(Long userProfileId);
}

