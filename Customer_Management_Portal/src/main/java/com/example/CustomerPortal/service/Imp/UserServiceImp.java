package com.example.CustomerPortal.service.Imp;

import com.example.CustomerPortal.customExceptions.UserNotFoundException;
import com.example.CustomerPortal.dto.request.UserRequestDto;
import com.example.CustomerPortal.dto.response.UserResponseDto;
import com.example.CustomerPortal.models.User;
import com.example.CustomerPortal.repository.UserRepository;
import com.example.CustomerPortal.service.UserService;
import com.example.CustomerPortal.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {
    final UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto) {
        User user = UserTransformer.userFromUserRequestDto(userRequestDto);

        //Just You have to save this User into the database and return that you created the User..
        User savedUser = userRepository.save(user);

        UserResponseDto userResponseDto = UserTransformer.userResponseDtoFromUser(savedUser);
        userResponseDto.setMessage("User Has Been Added to teh Db Successfully!!");

        return userResponseDto;
    }

    @Override
    public UserResponseDto updateUser(int id, UserRequestDto userRequestDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            //Instead of Throwing an error you can Save it as it was writtern in that doc..
            UserResponseDto dto = addUser(userRequestDto);
            dto.setMessage("We Didn't Found Anything Related to this Id so. We have created Your Account");
            return dto;
        }

        User user = optionalUser.get();
        user = UserTransformer.userFromUserDto(user, userRequestDto);

        User savedUser = userRepository.save(user);

        UserResponseDto responceDto = UserTransformer.userResponseDtoFromUser(savedUser);
        responceDto.setMessage("User With name:" + user.getFirstName() + " has been Updated");
        return responceDto;
    }

    @Override
    public UserResponseDto deleteUser(int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) throw new UserNotFoundException("User with id " + id + " is Not Found");
        userRepository.deleteById(id); //this wll delete the User
        User user = optionalUser.get();
        UserResponseDto responceDto = UserTransformer.userResponseDtoFromUser(user);
        responceDto.setMessage("User with id:" + user.getId() + " has been deleted Successfully!!");
        return responceDto;
    }

    @Override
    public List<UserResponseDto> getUsersBy(String search, String value) {
        List<User> userList;
        switch (search) {
            case "city": {
                userList = userRepository.findByCity(value);
                break;
            }
            case "phone": {
                userList = userRepository.findByPhone(value);
                break;
            }
            case "first": {
                userList = userRepository.findByFirstName(value);
                break;
            }
            case "email": {
                userList = userRepository.findByEmail(value);
                break;
            }
            default: {
               userList=userRepository.findAll();
            }
        }

        //else I'll have the value..

        //let's Convert the  Every User to UserResponse dto using our Transformer Function and I've actually Used
        List<UserResponseDto> userResponseDtos = userList.stream()
                .map(ele -> UserTransformer.userResponseDtoFromUser(ele))
                .collect(Collectors.toList());



        return userResponseDtos;
    }

    @Override
    public UserResponseDto getUser(int id) {
        Optional<User>optionalUser=userRepository.findById(id);
        if(optionalUser.isEmpty())throw new UserNotFoundException("Unable Find User with userId:"+id);

        User user=optionalUser.get();
        UserResponseDto dto=UserTransformer.userResponseDtoFromUser(user);
        dto.setMessage("User is Found");
        return dto;
    }
}
