package com.ec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ec.dto.UserDTO;
import com.ec.dto.UserResponseDTO;
import com.ec.entity.UserEntity;
import com.ec.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(
        name = "User APIs",
        description = "Operations related to user registration and profile management"
)
@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService service;

    @Operation(
            summary = "Register User",
            description = "Creates a new user account"
    )
    @PostMapping
    public UserEntity addUser(
            @Valid @RequestBody UserDTO userDTO) {

        UserEntity user = new UserEntity();

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());

        return service.saveUser(user);
    }

    @Operation(
            summary = "Get All Users",
            description = "Returns all registered users"
    )
    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return service.getAllUsers();
    }

    @Operation(
            summary = "Get User By Email",
            description = "Returns a user using their email address"
    )
    @GetMapping("/email/{email}")
    public UserResponseDTO getUserByEmail(
            @PathVariable String email) {

        return service.getUserByEmail(email);
    }

    @Operation(
            summary = "Check Email Availability",
            description = "Checks whether an email is already registered"
    )
    @GetMapping("/exists/{email}")
    public boolean checkEmail(
            @PathVariable String email) {

        return service.emailExists(email);
    }

    @Operation(
            summary = "My Profile",
            description = "Returns the currently logged-in user's profile"
    )
    @GetMapping("/me")
    public UserEntity getMyProfile() {

        return service.getLoggedInUser();
    }

}