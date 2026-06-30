package com.ec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ec.dto.LoginDTO;
import com.ec.dto.LoginResponseDTO;
import com.ec.entity.UserEntity;
import com.ec.repository.UserRepository;
import com.ec.security.JwtUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
        name = "Authentication APIs",
        description = "Operations related to user authentication and JWT login"
)
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository repo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Operation(
            summary = "User Login",
            description = "Authenticates a user and returns a JWT token"
    )
    @PostMapping("/login")
    public LoginResponseDTO login(
            @RequestBody LoginDTO request) {

        UserEntity user =
                repo.findByEmail(request.getEmail())
                        .orElse(null);

        if (user == null) {
            throw new RuntimeException("User Not Found");
        }

        if (!encoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new RuntimeException(
                    "Invalid Password");
        }

        String token =
                JwtUtil.generateToken(
                        user.getEmail(),
                        user.getRole().name());

        return new LoginResponseDTO(token);
    }

}