package com.example.SocialUserService.controller;
import com.example.SocialUserService.dto.UsersDTO;
import com.example.SocialUserService.security.JwtResponseEntity;
import com.example.SocialUserService.security.JwtService;
import com.example.SocialUserService.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;
    private final JwtService jwtService;

    public UsersController(UsersService usersService, JwtService jwtService) {
        this.usersService = usersService;
        this.jwtService = jwtService;
    }

    @PostMapping
    public ResponseEntity<UsersDTO> saveUser(@RequestBody UsersDTO usersDTO) {
        UsersDTO savedUserDTO = usersService.save(usersDTO, usersDTO.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUserDTO);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UsersDTO>> getAllUsers() {
        List<UsersDTO> usersDTOList = usersService.findAllUsers();
        return ResponseEntity.ok(usersDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersDTO> getUserById(@PathVariable String id) {
        UsersDTO userDTO = usersService.findById(id);
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsersDTO> updateUser(@PathVariable String id, @RequestBody UsersDTO usersDTO, @RequestParam(required = false) String newPassword) { // Changed to String
        usersDTO.setId(id);
        UsersDTO updatedUserDTO = usersService.update(usersDTO, newPassword);
        return ResponseEntity.ok(updatedUserDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        usersService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseEntity> loginUser(@RequestBody UsersDTO usersDTO){
        UsersDTO user = usersService.login(usersDTO.getEmail(),usersDTO.getPassword());
        String token = jwtService.generateJwt(user);
        JwtResponseEntity response = new JwtResponseEntity("Login Successful!! Here is your token!",token);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/validateToken")
    public ResponseEntity<String> validateToken(@RequestParam("token") String token){
        jwtService.validateToken(token);
        return ResponseEntity.ok("Token is valid!!");
    }
}

