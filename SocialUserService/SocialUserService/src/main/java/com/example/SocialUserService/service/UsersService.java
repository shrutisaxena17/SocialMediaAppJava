package com.example.SocialUserService.service;

import com.example.SocialUserService.dto.UsersDTO;

import java.util.List;



public interface UsersService {
    UsersDTO save(UsersDTO usersDTO, String password);

    void deleteById(String id);

    UsersDTO findById(String id);

    List<UsersDTO> findAllUsers();

    UsersDTO update(UsersDTO usersDTO, String newPassword);

    UsersDTO login(String email, String password);
}
