package com.blog.controller.rest;

import com.blog.dto.ApiResponse;
import com.blog.dto.UserDTO;
import com.blog.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ApiResponse<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        return ApiResponse.success("User created successfully", userService.createUser(userDTO));
    }

    @GetMapping("/{id}")
    public ApiResponse<UserDTO> getUser(@PathVariable Long id) {
        return ApiResponse.success("User retrieved successfully", userService.getUserById(id));
    }

    @GetMapping
    public ApiResponse<List<UserDTO>> getAllUsers() {
        return ApiResponse.success("Users retrieved successfully", userService.getAllUsers());
    }

    @PutMapping("/{id}")
    public ApiResponse<UserDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
        return ApiResponse.success("User updated successfully", userService.updateUser(id, userDTO));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ApiResponse.success("User deleted successfully", null);
    }
}
