package com.react_springboot_app.dto.converter;

import com.react_springboot_app.dto.request.UserRequestDto;
import com.react_springboot_app.dto.response.UserResponseDto;
import com.react_springboot_app.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverter {

    // USERREQUESTDTO -> USER
    public User toUser(UserRequestDto userRequestDto) {
        User user = new User();
        user.setFirstName(userRequestDto.firstName());
        user.setLastName(userRequestDto.lastName());
        user.setUsername(userRequestDto.username());
        user.setEmail(userRequestDto.email());
        return user;
    }

    // USER -> USERRESPONSEDTO
    public UserResponseDto toUserResponseDto(User user) {
        return new UserResponseDto(
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getEmail()
        );
    }

    // LIST( USER -> USERREQUESTDTO )
    public List<UserResponseDto> toUserResponseDtoList(List<User> userList) {
        return userList.stream()
                .map(this::toUserResponseDto)
                .toList();
    }

}
