package com.example.SocialUserService.repo;

import com.example.SocialUserService.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepo extends JpaRepository<Users, String> {

    Optional<Users> findByIdAndIsDeletedFalse(String id);

    List<Users> findAllByIsDeletedFalse();

    Optional<Users> findByEmail(String email);
}
