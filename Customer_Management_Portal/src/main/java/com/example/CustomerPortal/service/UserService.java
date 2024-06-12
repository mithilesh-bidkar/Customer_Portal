package com.example.CustomerPortal.service;

import com.example.CustomerPortal.dto.request.UserRequestDto;
import com.example.CustomerPortal.dto.response.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto addUser(UserRequestDto userRequestDto);
    UserResponseDto updateUser(int id, UserRequestDto userRequestDto);

    UserResponseDto deleteUser(int id);

    List<UserResponseDto>getUsersBy(String search, String value);

    UserResponseDto getUser(int id);
}
