package com.ec.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ec.dto.UserResponseDTO;
import com.ec.entity.UserEntity;
import com.ec.exception.UserNotFoundException;
import com.ec.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public UserEntity saveUser(UserEntity user) {

        if (repo.existsByEmail(user.getEmail())) {
            throw new RuntimeException(
                    "Email already exists");
        }

        user.setPassword(
                encoder.encode(
                        user.getPassword()));

        return repo.save(user);
    }

    public List<UserResponseDTO> getAllUsers() {

        return repo.findAll()
                .stream()
                .map(UserResponseDTO::new)
                .toList();
    }

    public boolean emailExists(String email) {
        return repo.existsByEmail(email);
    }

    public UserResponseDTO getUserByEmail(String email) {

        UserEntity user = repo.findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException("User Not Found"));

        return new UserResponseDTO(user);
    }

    public UserEntity getLoggedInUser() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return repo.findByEmail(email)
                .orElseThrow(() ->
                new UserNotFoundException("User not found"));
    }
}