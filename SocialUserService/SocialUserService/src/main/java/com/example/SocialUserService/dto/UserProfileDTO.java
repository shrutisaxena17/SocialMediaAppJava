package com.example.SocialUserService.dto;

import java.util.List;

public class UserProfileDTO {
    private String userId;
    private String name;
    private String bio;
    private String profilePictureUrl;
    private List<String> skills;
    private int followersCount;
    private int followingCount;

    public UserProfileDTO() {
    }

    public UserProfileDTO(String userId, String name, String bio, String profilePictureUrl, List<String> skills, int followersCount, int followingCount) {
        this.userId = userId;
        this.name = name;
        this.bio = bio;
        this.profilePictureUrl = profilePictureUrl;
        this.skills = skills;
        this.followersCount = followersCount;
        this.followingCount = followingCount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(int followingCount) {
        this.followingCount = followingCount;
    }
}

