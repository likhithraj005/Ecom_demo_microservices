package com.likhith.microservices.usermservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.likhith.microservices.usermservice.dto.UserRequest;
import com.likhith.microservices.usermservice.dto.UserResponse;
import com.likhith.microservices.usermservice.model.User;
import com.likhith.microservices.usermservice.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    // CREATE
    public UserResponse create(UserRequest request) {

        log.info("Creating user with email: {}", request.getEmail());

        User user = mapToEntity(request);
        User savedUser = userRepository.save(user);

        log.info("User created successfully with ID: {}", savedUser.getId());

        return mapToDto(savedUser);
    }

    // GET ALL
    public List<UserResponse> getAll() {

        log.info("Fetching all users");

        List<UserResponse> users = userRepository.findAll()
                .stream()
                .map(user -> mapToDto(user))
                .collect(Collectors.toList());

        log.info("Total users fetched: {}", users.size());

        return users;
    }

    // GET BY ID
    public UserResponse getById(Long id) {

        log.info("Fetching user with ID: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("User not found with ID: {}", id);
                    return new RuntimeException("User not found");
                });

        return mapToDto(user);
    }

    // UPDATE
    public UserResponse update(Long id, UserRequest updatedRequest) {

        log.info("Updating user with ID: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("User not found for update. ID: {}", id);
                    return new RuntimeException("User not found");
                });

        user.setName(updatedRequest.getName());
        user.setEmail(updatedRequest.getEmail());

        User updatedUser = userRepository.save(user);

        log.info("User updated successfully. ID: {}", id);

        return mapToDto(updatedUser);
    }

    // DELETE
    public void delete(Long id) {

        log.info("Deleting user with ID: {}", id);

        userRepository.deleteById(id);

        log.info("User deleted successfully. ID: {}", id);
    }

    // DTO → ENTITY
    private User mapToEntity(UserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        return user;
    }

    // ENTITY → DTO
    private UserResponse mapToDto(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        return response;
    }
}
