package com.example.service.controller;

import com.example.service.dao.User;
import com.example.service.dto.UserDTO;
import com.example.service.dto.response.ServiceResponse;
import com.example.service.service.user.UserService;
import com.example.service.service.user.UserValidator;
import com.example.service.service.userhistory.UserHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;

    private UserHistoryService userHistoryService;

    private UserValidator userValidator;

    private ConsumerTokenServices tokenServices;

    public UserController(UserService userService, UserHistoryService userHistoryService, UserValidator userValidator, ConsumerTokenServices tokenServices) {
        this.userService = userService;
        this.userHistoryService = userHistoryService;
        this.userValidator = userValidator;
        this.tokenServices = tokenServices;
    }

    @PostMapping("/user")
    public ResponseEntity<ServiceResponse> createUser(@Valid @RequestBody UserDTO user) {
        userValidator.validateUserDTO(user);
        userService.saveOrUpdate(user.toUserDAO());
        return ResponseEntity.status(HttpStatus.CREATED).body(ServiceResponse.builder()
                .code("200")
                .message("Success")
                .build()
        );
    }

    @GetMapping("/user/histories")
    public ResponseEntity<ServiceResponse> getUserHistory(@RequestParam(value = "numberOfRecord", required = false, defaultValue = "5") int numberOfRecord, Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        return ResponseEntity.ok(
                ServiceResponse.builder()
                        .code("200")
                        .message("Success")
                        .data(userHistoryService.getLastedUserHistory(user, numberOfRecord))
                        .build()
        );
    }

    @GetMapping("/me")
    public Principal getMyPrincipal(Principal principal) {
        return principal;
    }

    @GetMapping("/user/logout")
    public ResponseEntity<ServiceResponse> logout(@RequestParam("access_token") String accessToken) {
        tokenServices.revokeToken(accessToken);
        return ResponseEntity.ok(
                ServiceResponse.builder()
                        .code("200")
                        .message("Success")
                        .build()
        );
    }

}
