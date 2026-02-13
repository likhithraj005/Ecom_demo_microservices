package com.likhith.microservices.usermservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.likhith.microservices.usermservice.dto.UserRequest;
import com.likhith.microservices.usermservice.dto.UserResponse;
import com.likhith.microservices.usermservice.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    // CREATE
    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest request) {

        log.info("Received request to create user");

        UserResponse response = userService.create(request);

        log.info("User creation completed");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {

        log.info("Received request to fetch all users");

        return ResponseEntity.ok(userService.getAll());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id) {

        log.info("Received request to fetch user with ID: {}", id);

        return ResponseEntity.ok(userService.getById(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(
            @PathVariable Long id,
            @RequestBody UserRequest request) {

        log.info("Received request to update user with ID: {}", id);

        return ResponseEntity.ok(userService.update(id, request));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {

        log.info("Received request to delete user with ID: {}", id);

        userService.delete(id);

        return ResponseEntity.ok("User deleted successfully");
    }
}
