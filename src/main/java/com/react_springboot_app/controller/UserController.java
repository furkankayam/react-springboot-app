package com.react_springboot_app.controller;

import com.react_springboot_app.dto.request.UserRequestDto;
import com.react_springboot_app.dto.response.UserResponseDto;
import com.react_springboot_app.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "Basic App")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // CREATE
    @Operation(
            description = "Create User",
            responses = {
                    @ApiResponse(
                            description = "Create",
                            responseCode = "201"
                    ),
                    @ApiResponse(
                            description = "Error",
                            responseCode = "404"
                    )
            }
    )
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {
        return new ResponseEntity<>(userService.createUser(userRequestDto), HttpStatus.CREATED);
    }

    @Operation(
            description = "Update User",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Error",
                            responseCode = "404"
                    )
            }
    )
    // UPDATE
    @PostMapping("/{username}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable("username") String username,
                                                      @RequestBody UserRequestDto userRequestDto) {
        return new ResponseEntity<>(userService.updateUser(username, userRequestDto), HttpStatus.OK);
    }

    @Operation(
            description = "All Users",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Error",
                            responseCode = "404"
                    )
            }
    )
    // ALL USERS
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @Operation(
            description = "Delete User",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Error",
                            responseCode = "404"
                    )
            }
    )
    // DELETE
    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable("username") String username) {
        userService.deleteUser(username);
    }

    @Operation(
            description = "Search Users",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Error",
                            responseCode = "404"
                    )
            }
    )
    // SEARCH
    @GetMapping("/search/{searchUsers}")
    public ResponseEntity<List<UserResponseDto>> searchUsers(@PathVariable("searchUsers") String searchUsers) {
        return new ResponseEntity<>(userService.searchUsers(searchUsers), HttpStatus.OK);
    }

}
