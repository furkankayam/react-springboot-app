package com.react_springboot_app.service;

import com.react_springboot_app.dto.converter.UserConverter;
import com.react_springboot_app.dto.request.UserRequestDto;
import com.react_springboot_app.dto.response.UserResponseDto;
import com.react_springboot_app.exception.UserNotFoundException;
import com.react_springboot_app.model.User;
import com.react_springboot_app.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public UserService(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    // CREATE
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        User user = userConverter.toUser(userRequestDto);
        User savedUser = userRepository.save(user);
        return userConverter.toUserResponseDto(savedUser);
    }

    // UPDATE
    public UserResponseDto updateUser(String username,
                                      UserRequestDto userRequestDto) {
        User user = isThereUser(username);
        user.setUsername(userRequestDto.username());
        user.setFirstName(userRequestDto.firstName());
        user.setLastName(userRequestDto.lastName());
        user.setEmail(userRequestDto.email());
        User savedUser = userRepository.save(user);
        return userConverter.toUserResponseDto(savedUser);
    }

    // ALL USERS
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userConverter.toUserResponseDtoList(users);
    }

    // DELETE
    public void deleteUser(String username) {
        User user = isThereUser(username);
        userRepository.delete(user);
    }

    // SEARCH
    public List<UserResponseDto> searchUsers(String searchUsers) {
        List<User> users = userRepository.searchUsers(searchUsers);
        return userConverter.toUserResponseDtoList(users);
    }

    // IS THERE USER
    public User isThereUser(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException("User Not Found! username: " + username)
        );
    }

}
