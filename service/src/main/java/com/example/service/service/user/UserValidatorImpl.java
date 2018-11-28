package com.example.service.service.user;

import com.example.service.dto.UserDTO;
import com.example.service.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserValidatorImpl implements UserValidator {

    private UserService userService;

    public UserValidatorImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void validateUserDTO(UserDTO userDTO) {
        validateUniqueUserName(userDTO.getUsername());
        validatePasswordMatching(userDTO);
    }

    private void validatePasswordMatching(UserDTO userDTO) {
        if (!userDTO.getPassword().equals(userDTO.getConfirmedPassword())) {
            throw new UserException(HttpStatus.BAD_REQUEST, "400-001", "Password is not match");
        }
    }

    private void validateUniqueUserName(String username) {
        if (userService.findByUsername(username) != null) {
            throw new UserException(HttpStatus.BAD_REQUEST, "400-002", "Username is already exists");
        }
    }
}
