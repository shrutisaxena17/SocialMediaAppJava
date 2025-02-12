package com.example.SocialUserService.dto;

import java.time.LocalDate;

public class WorkExperienceDTO {
    private Long id;
    private String company;
    private String position;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isCurrentJob;
    private String description;
    private Long userProfileId;

    public WorkExperienceDTO() {
    }

    public WorkExperienceDTO(Long id, String company, String position, LocalDate startDate, LocalDate endDate, boolean isCurrentJob, String description, Long userProfileId) {
        this.id = id;
        this.company = company;
        this.position = position;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isCurrentJob = isCurrentJob;
        this.description = description;
        this.userProfileId = userProfileId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isCurrentJob() {
        return isCurrentJob;
    }

    public void setCurrentJob(boolean currentJob) {
        isCurrentJob = currentJob;
        if (currentJob) {
            this.endDate = null;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUserProfileId() {
        return userProfileId;
    }

    public void setUserProfileId(Long userProfileId) {
        this.userProfileId = userProfileId;
    }
}
