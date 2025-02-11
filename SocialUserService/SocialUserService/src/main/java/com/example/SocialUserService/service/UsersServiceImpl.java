package com.example.SocialUserService.service;

import com.example.SocialUserService.dto.UsersDTO;
import com.example.SocialUserService.entity.UserRole;
import com.example.SocialUserService.entity.Users;
import com.example.SocialUserService.repo.UsersRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepo usersRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UsersServiceImpl(UsersRepo usersRepo, BCryptPasswordEncoder passwordEncoder) {
        this.usersRepo = usersRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UsersDTO save(UsersDTO usersDTO, String password) {
        Users users = convertToEntity(usersDTO);

        if (password != null && !password.isEmpty()) {
            String encodedPassword = passwordEncoder.encode(password);
            users.setPassword(encodedPassword);
        }

        if (users.getRole() == null) {
            users.setRole(UserRole.USER);
        }

        Users savedUser = usersRepo.save(users);
        return convertToDTO(savedUser);
    }

    @Override
    public UsersDTO update(UsersDTO usersDTO, String newPassword) {
        String userId = usersDTO.getId();

        Users existingUser = usersRepo.findByIdAndIsDeletedFalse(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found!"));

        existingUser.setEmail(usersDTO.getEmail());
        existingUser.setRole(usersDTO.getRole());

        if (newPassword != null && !newPassword.isEmpty() && !newPassword.equals(existingUser.getPassword())) {
            existingUser.setPassword(passwordEncoder.encode(newPassword));
        }

        Users updatedUser = usersRepo.save(existingUser);
        return convertToDTO(updatedUser);
    }

    @Override
    public UsersDTO login(String email, String password) {
        Users users = usersRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid email address. Please try again!"));

        if (!passwordEncoder.matches(password, users.getPassword())) {
            throw new RuntimeException("Sorry! Invalid Username or Password!");
        }

        return convertToDTO(users);
    }

    @Override
    public void deleteById(String id) {
        Users user = usersRepo.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("User Not Found!"));
        user.setDeleted(true);
        usersRepo.save(user);
    }

    @Override
    public UsersDTO findById(String id) {
        Users user = usersRepo.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("User Not Found!"));
        return convertToDTO(user);
    }

    @Override
    public List<UsersDTO> findAllUsers() {
        return usersRepo.findAllByIsDeletedFalse()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private UsersDTO convertToDTO(Users users) {
        return new UsersDTO(users.getId(), users.getEmail(), users.isDeleted(), users.getRole());
    }

    private Users convertToEntity(UsersDTO usersDTO) {
        return new Users(usersDTO.getId(), usersDTO.getEmail(), usersDTO.isDeleted(), usersDTO.getRole());
    }
}
